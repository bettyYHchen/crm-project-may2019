package com.busyqa.crm.services;

import com.busyqa.crm.message.request.StudentRequest;
import com.busyqa.crm.message.response.StudentResponse;
import com.busyqa.crm.model.academic.TrainingClass;
import com.busyqa.crm.model.user.CoopStatus;
import com.busyqa.crm.model.user.Intern;
import com.busyqa.crm.model.user.Position;
import com.busyqa.crm.model.user.Student;
import com.busyqa.crm.model.user.payment.Payment;
import com.busyqa.crm.model.user.payment.PaymentStatus;
import com.busyqa.crm.repo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class StudentService {
    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private TrainingClassRepository trainingClassRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private InternRepository internRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PaymentService paymentService;

    public List<StudentResponse> listStudents() {
        List<Student> students = studentRepository.findAllIfNotDropOff();
        if (students.isEmpty()) throw new RuntimeException("Empty student list!");
        List<StudentResponse> studentResponses = new ArrayList<>();
        System.out.println(students.size());
        for (Student s: students) {
            String[] tmp = s.getName().split(" ");
            String firstName = tmp[0];
            String lastName = tmp[1];
            studentResponses.add(new StudentResponse(s.getId(),firstName, lastName,
                    s.getPhone(), s.getEmail(), s.getPaymentPlan(),
                    s.getPaymentPlanStatus(), s.getPaymentPlanAgreement(),
                    s.getTrainingClass().getName(), s.getComment(), s.getStatusAsOfDay(),
                    s.getModifiedTime(), s.getAmountPaid(), s.getRemainingBalance(),
                    s.finishedClass(), s.getDropOff()));

        }
        return studentResponses;
    }

    public StudentResponse listStudentByEmail(String email) {
        Student student = studentRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("Error: student not found!"));
        String[] tmp = student.getName().split(" ");
        String firstName = tmp[0];
        String lastName = tmp[1];
//        Pageable pageable = PageRequest.of(0, 30);
//        Page<Payment> paymentsPage = paymentRepository.findByUserId(student.getId(), pageable);
//        List<Payment> payments = paymentsPage.getContent();
        List<Payment> payments = paymentService.updatePayments(student.getId(),student.getTrainingClass().getName());
        double feeNeedToPay = 0;
        for (Payment p : payments) {
            if (p.getStatus().equals(PaymentStatus.UNPAID.toString())) {
                feeNeedToPay += p.getAmount();
            }else if (p.getStatus().equals(PaymentStatus.PAID.toString())) {
                feeNeedToPay += p.getLateFee();
            }
        }
        student.setAmountPaid(paymentService.getUpdatePaidAmount(student.getId()));
        student.setRemainingBalance(feeNeedToPay);
        studentRepository.save(student);
        return new StudentResponse(student.getId(),firstName, lastName, student.getPhone(), student.getEmail(), student.getPaymentPlan(),
                student.getPaymentPlanStatus(), student.getPaymentPlanAgreement(),
                student.getTrainingClass().getName(), student.getComment(), student.getStatusAsOfDay(), student.getModifiedTime(),
                student.getAmountPaid(), student.getRemainingBalance(), student.finishedClass(), student.getDropOff());
    }

    public ResponseEntity<StudentResponse> updateStudent(String email, StudentRequest studentRequest) {

        return studentRepository.findByEmail(email).map(recordUpdated -> {
            TrainingClass trainingClass = trainingClassRepository.findByName(studentRequest.getaTrainingClassName()).
                    orElseThrow(() -> new RuntimeException("Error: TrainingClass not found!"));
            String name = studentRequest.getFirstName() + " " + studentRequest.getLastName();
            recordUpdated.setName(name);

            recordUpdated.setUsername(studentRequest.getEmail());
            recordUpdated.setEmail(studentRequest.getEmail());
            recordUpdated.setPhone(studentRequest.getPhone());
            recordUpdated.setPaymentPlan(studentRequest.getPaymentPlan());
            recordUpdated.setPaymentPlanStatus(studentRequest.getPaymentPlanStatus());
            recordUpdated.setPaymentPlanAgreement(studentRequest.getPaymentPlanAgreement());
            recordUpdated.setTrainingClass(trainingClass);
            recordUpdated.setComment(studentRequest.getComment());
            recordUpdated.setAmountPaid(studentRequest.getAmountPaid());
            recordUpdated.updateBalance();
            recordUpdated.setModifiedTime(LocalDateTime.now().toString());
            recordUpdated.setDropOff(studentRequest.getDropOff());
            this.studentRepository.save(recordUpdated);
            StudentResponse studentResponse = new StudentResponse();
            BeanUtils.copyProperties(studentRequest,studentResponse);
            studentResponse.setId(recordUpdated.getId());
            return ResponseEntity.ok().body(studentResponse);
        }).orElse(ResponseEntity.notFound().build());

    }

    public ResponseEntity<?> deleteStudent(String email) {
        return studentRepository.findByEmail(email).map(
                record -> {
                    studentRepository.deleteByEmail(email);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());

    }

    public Intern changeStudentToIntern(String email) {

        Student student = studentRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Fail! -> Cause: student not found."));
        List<Position> positions = positionRepository.findAll();
        for (Position p: positions) {
            student.removePosition(p);
            positionRepository.save(p);
        }
        Position position = positionRepository.findByRoleNameAndTeamName("ROLE_CLIENT","TEAM_INTERN").
                orElseThrow(() -> new RuntimeException("Error: position not found!"));


        String coopStatus;
        if ((student.getRemainingBalance() != 0) && (!student.finishedClass())) throw
            new RuntimeException("Cannot convert to intern. Please make sure the student is either paid in full or has finished the training!");

        if (!student.finishedClass()) { coopStatus = CoopStatus.PENDING_TO_START_UNFINISHED.toString();}
        else if (student.getRemainingBalance() != 0) { coopStatus = CoopStatus.PENDING_TO_START_FEE_NOT_CLEAR.toString();}
        else { coopStatus = CoopStatus.IN_PROGRESS.toString();}

        LocalDate today = LocalDate.now();
        LocalDate threeMonthsAfter = today.plus(90, ChronoUnit.DAYS);
        Intern intern = new Intern();
        BeanUtils.copyProperties(student,intern);
        intern.setaTrainingClassName(student.getTrainingClass().getName());
        intern.setCoopStatus(coopStatus);
        intern.setCoopStartDate(today.toString());
        intern.setCoopEndDate(threeMonthsAfter.toString());
        intern.setProjectAssigned("");
        intern.setPerformance("");
        intern.setClassFee(student.getClassFee());
        intern.addPosition(position);


        // update payments
        Pageable pageable = PageRequest.of(0, 30);
        Page<Payment> paymentsPage = paymentRepository.findByUserId(student.getId(), pageable);
        List<Payment> payments = paymentsPage.getContent();
        studentRepository.deleteByEmail(email);

        Intern savedIntern = internRepository.save(intern);
        for (Payment p: payments) {
            p.setUser(savedIntern);
            paymentRepository.save(p);
        }

        return savedIntern;

    }




}
