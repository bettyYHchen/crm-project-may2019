package com.busyqa.crm.services;

import com.busyqa.crm.message.request.StudentRequest;
import com.busyqa.crm.message.response.StudentResponse;
import com.busyqa.crm.model.academic.TrainingClass;
import com.busyqa.crm.model.user.CoopStatus;
import com.busyqa.crm.model.user.Intern;
import com.busyqa.crm.model.user.Position;
import com.busyqa.crm.model.user.Student;
import com.busyqa.crm.repo.InternRepository;
import com.busyqa.crm.repo.PositionRepository;
import com.busyqa.crm.repo.StudentRepository;
import com.busyqa.crm.repo.TrainingClassRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public List<StudentResponse> listStudents() {
        List<Student> students = studentRepository.findAll();
        if (students.isEmpty()) throw new RuntimeException("Empty student list!");
        List<StudentResponse> studentResponses = new ArrayList<>();
        System.out.println(students.size());
        for (Student s: students) {
            String[] tmp = s.getName().split(" ");
            String firstName = tmp[0];
            String lastName = tmp[1];
            studentResponses.add(new StudentResponse(firstName,lastName,
                    s.getPhone(), s.getEmail(), s.getPaymentPlan(),s.getRemainingBalance(),
                    s.finishedClass(), s.getTrainingClass().getName()));

        }
        return studentResponses;
    }

    public StudentResponse listStudentByEmail(String email) {
        Student student = studentRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("Error: student not found!"));
        String[] tmp = student.getName().split(" ");
        String firstName = tmp[0];
        String lastName = tmp[1];
        return new StudentResponse(firstName,lastName,
                student.getPhone(), student.getEmail(), student.getPaymentPlan(),student.getRemainingBalance(),
                student.finishedClass(), student.getTrainingClass().getName());
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
            recordUpdated.setRemainingBalance(studentRequest.getRemainingBalance());
            recordUpdated.setTrainingClass(trainingClass);
            recordUpdated.setStatusAsOfDay(LocalDateTime.now().toString());
            this.studentRepository.save(recordUpdated);
            StudentResponse studentResponse = new StudentResponse();
            BeanUtils.copyProperties(studentRequest,studentResponse);
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
        Set<Position> newPositionSet = new HashSet<>();
        newPositionSet.add(position);

        String coopStatus;
        if ((student.getRemainingBalance() != 0) && (!student.finishedClass())) throw
            new RuntimeException("Cannot convert to intern. Please make sure the student is either paid in full or has finished the training!");

        if (!student.finishedClass()) { coopStatus = CoopStatus.PENDING_TO_START_UNFINISHED.toString();}
        else if (student.getRemainingBalance() != 0) { coopStatus = CoopStatus.PENDING_TO_START_FEE_NOT_CLEAR.toString();}
        else { coopStatus = CoopStatus.IN_PROGRESS.toString();}

        Intern intern = new Intern(student.getName(),student.getUsername(),student.getEmail(),student.getPassword(), newPositionSet,
                student.getStatus(),student.getStatusAsOfDay(),student.getPhone(), coopStatus, student.getTrainingClass().getName());


//        Student student = new Student(student.getFirstName(), student.getLastName(), student.getPhone(), student.getEmail(),
//                trainingClass, PaymentPlan.OneTime.toString(), trainingClass.getCourseFee()-300);

        studentRepository.deleteByEmail(email);
        internRepository.save(intern);
        return intern;

    }




}
