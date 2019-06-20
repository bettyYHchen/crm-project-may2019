package com.busyqa.crm.services;

import com.busyqa.crm.message.request.InternRequest;
import com.busyqa.crm.message.response.InternResponse;
import com.busyqa.crm.model.user.Intern;
import com.busyqa.crm.model.user.Position;
import com.busyqa.crm.model.user.Resume;
import com.busyqa.crm.model.user.payment.Payment;
import com.busyqa.crm.repo.InternRepository;
import com.busyqa.crm.repo.PaymentRepository;
import com.busyqa.crm.repo.PositionRepository;
import com.busyqa.crm.repo.ResumeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class InternService {
    @Autowired
    private PositionRepository positionRepository;


    @Autowired
    private InternRepository internRepository;

    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    public List<InternResponse> listInterns() {
        List<Intern> interns = internRepository.findAll();
        if (interns.isEmpty()) throw new RuntimeException("Empty intern list!");
        List<InternResponse> internResponses = new ArrayList<>();
        System.out.println(interns.size());
        for (Intern i: interns) {
            String[] tmp = i.getName().split(" ");
            String firstName = tmp[0];
            String lastName = tmp[1];
            internResponses.add(new InternResponse(i.getId(),firstName, lastName, i.getPhone(), i.getEmail(), i.getPaymentPlan(),
                    i.getPaymentPlanStatus(), i.getPaymentPlanAgreement(), i.getaTrainingClassName(), i.getStatusAsOfDay(),
                    i.getModifiedTime(), i.getAmountPaid(), i.getRemainingBalance(), i.getCoopStatus(), i.getCoopStartDate(),
                    i.getCoopEndDate(), i.getProjectAssigned(), i.getPerformance()));

        }
        return internResponses;
    }

    public InternResponse listInternByEmail(String email) {
        Intern intern = internRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("Error: intern not found!"));
        String[] tmp = intern.getName().split(" ");
        String firstName = tmp[0];
        String lastName = tmp[1];
        Pageable pageable = PageRequest.of(0, 30);
        Page<Payment> paymentsPage = paymentRepository.findByUserId(intern.getId(), pageable);
        List<Payment> payments = paymentsPage.getContent();
        int amountPaid = 0;
        for (Payment p : payments) {
            amountPaid += p.getPaidAmount();
        }
        intern.setAmountPaid(amountPaid);
        intern.setRemainingBalance(intern.getClassFee()-amountPaid);
        internRepository.save(intern);
        return new InternResponse(intern.getId(),firstName, lastName, intern.getPhone(), intern.getEmail(), intern.getPaymentPlan(),
                intern.getPaymentPlanStatus(), intern.getPaymentPlanAgreement(), intern.getaTrainingClassName(), intern.getStatusAsOfDay(),
                intern.getModifiedTime(), intern.getAmountPaid(), intern.getRemainingBalance(), intern.getCoopStatus(), intern.getCoopStartDate(),
                intern.getCoopEndDate(), intern.getProjectAssigned(), intern.getPerformance());
    }

    public ResponseEntity<InternResponse> updateIntern(String email, InternRequest internRequest) {

        return internRepository.findByEmail(email).map(recordUpdated -> {
            String name = internRequest.getFirstName() + " " + internRequest.getLastName();
            recordUpdated.setName(name);
            recordUpdated.setUsername(internRequest.getEmail());
            recordUpdated.setEmail(internRequest.getEmail());
            recordUpdated.setPhone(internRequest.getPhone());
            recordUpdated.setPaymentPlan(internRequest.getPaymentPlan());
            recordUpdated.setPaymentPlanStatus(internRequest.getPaymentPlanStatus());
            recordUpdated.setAmountPaid(internRequest.getAmountPaid());
            recordUpdated.setCoopStatus(internRequest.getCoopStatus());
            recordUpdated.setCoopStartDate(internRequest.getCoopStartDate());
            recordUpdated.setCoopEndDate(internRequest.getCoopEndDate());
            recordUpdated.setProjectAssigned(internRequest.getProjectAssigned());
            recordUpdated.setPerformance(internRequest.getPerformance());
            recordUpdated.setModifiedTime(LocalDateTime.now().toString());
            this.internRepository.save(recordUpdated);
            InternResponse internResponse = new InternResponse();
            BeanUtils.copyProperties(internRequest,internResponse);
            internResponse.setId(recordUpdated.getId());
            return ResponseEntity.ok().body(internResponse);
        }).orElse(ResponseEntity.notFound().build());

    }

    public ResponseEntity<?> deleteIntern(String email) {
        return internRepository.findByEmail(email).map(
                record -> {
                    internRepository.deleteByEmail(email);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());

    }

    public Resume changeInternToResume(String email) {

        Intern intern = internRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Fail! -> Cause: Intern not found."));
        List<Position> positions = positionRepository.findAll();
        for (Position p: positions) {
            intern.removePosition(p);
            positionRepository.save(p);
        }
        Position position = positionRepository.findByRoleNameAndTeamName("ROLE_CLIENT","TEAM_RESUME").
                orElseThrow(() -> new RuntimeException("Error: position not found!"));
        Resume resume = new Resume();
        LocalDate today = LocalDate.now();
        LocalDate tenDaysAfter = today.plus(10, ChronoUnit.DAYS);
        BeanUtils.copyProperties(intern,resume);
        resume.setStatusAsOfDay(LocalDateTime.now().toString());
        resume.setModifiedTime(LocalDateTime.now().toString());
        resume.setResumeStartDate(today.toString());
        resume.setResumeEndDate(tenDaysAfter.toString());


        // update payments
        Pageable pageable = PageRequest.of(0, 30);
        Page<Payment> paymentsPage = paymentRepository.findByUserId(intern.getId(), pageable);
        List<Payment> payments = paymentsPage.getContent();
        internRepository.deleteByEmail(email);

        Resume savedResume = resumeRepository.save(resume);
        for (Payment p: payments) {
            p.setUser(savedResume);
            paymentRepository.save(p);
        }
        return savedResume;

    }
}
