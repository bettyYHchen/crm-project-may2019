package com.busyqa.crm.services;


import com.busyqa.crm.message.request.LeadRequest;
import com.busyqa.crm.message.response.LeadResponse;
import com.busyqa.crm.model.user.Lead;
import com.busyqa.crm.model.user.Position;
import com.busyqa.crm.model.user.Student;
import com.busyqa.crm.model.academic.TrainingClass;
import com.busyqa.crm.repo.LeadRepository;
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
public class LeadService {

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private LeadRepository leadRepository;

    @Autowired
    private TrainingClassRepository trainingClassRepository;

    @Autowired
    private StudentRepository studentRepository;

    public List<LeadResponse> listLeads() {
        List<Lead> leads = leadRepository.findAll();
        if (leads.isEmpty()) throw new RuntimeException("Empty lead list!");
        List<LeadResponse> leadResponses = new ArrayList<>();
        System.out.println(leads.size());
        for (Lead l: leads) {
            String[] tmp = l.getName().split(" ");
            String firstName = tmp[0];
            String lastName = tmp[1];
            System.out.println(l.getPhone());
            System.out.println(l.getEmail());
            System.out.println(l.getPaidDeposit());
            System.out.println(l.getPaymentPlan());
            leadResponses.add(new LeadResponse(firstName,lastName,
                    l.getPhone(),l.getEmail(), l.getPaidDeposit(),
                    l.getPaymentPlan(), l.getPaymentPlanStatus(), l.getPaymentPlanAgreement(),
                    l.getLeadSource(),l.getLeadStatus(),l.getaTrainingClassName(),l.getComment(),l.getStatusAsOfDay(),l.getModifiedTime()));

        }
        return leadResponses;
    }

    public LeadResponse listLeadByEmail(String email) {
        Lead lead = leadRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("Error: Lead not found!"));
        String[] tmp = lead.getName().split(" ");
        String firstName = tmp[0];
        String lastName = tmp[1];
        return new LeadResponse(firstName,lastName,
                lead.getPhone(),lead.getEmail(), lead.getPaidDeposit(),
                lead.getPaymentPlan(), lead.getPaymentPlanStatus(), lead.getPaymentPlanAgreement(),
                lead.getLeadSource(),lead.getLeadStatus(),lead.getaTrainingClassName(),lead.getComment(),lead.getStatusAsOfDay(),lead.getModifiedTime());

    }




    public ResponseEntity<LeadResponse> updateLead(String email, LeadRequest leadRequest) {

        return leadRepository.findByEmail(email).map(recordUpdated -> {
            String name = leadRequest.getFirstName() + " " + leadRequest.getLastName();
            recordUpdated.setName(name);
            recordUpdated.setUsername(leadRequest.getEmail());
            recordUpdated.setEmail(leadRequest.getEmail());
            recordUpdated.setPhone(leadRequest.getPhone());
            recordUpdated.setPaidDeposit(leadRequest.getPaidDeposit());
            recordUpdated.setPaymentPlan(leadRequest.getPaymentPlan());
            recordUpdated.setPaymentPlanStatus(leadRequest.getPaymentPlanStatus());
            recordUpdated.setPaymentPlanAgreement(leadRequest.getPaymentPlanAgreement());
            recordUpdated.setLeadSource(leadRequest.getLeadSource());
            recordUpdated.setLeadStatus(leadRequest.getLeadStatus());
            recordUpdated.setaTrainingClassName(leadRequest.getaTrainingClassName());
            recordUpdated.setComment(leadRequest.getComment());
            recordUpdated.setModifiedTime(LocalDateTime.now().toString());
            this.leadRepository.save(recordUpdated);
            LeadResponse leadResponse = new LeadResponse();
            BeanUtils.copyProperties(leadRequest,leadResponse);
            return ResponseEntity.ok().body(leadResponse);
                 }).orElse(ResponseEntity.notFound().build());

    }

    public ResponseEntity<?> deleteLead(String email) {
        return leadRepository.findByEmail(email).map(
                record -> {
                    leadRepository.deleteByEmail(email);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());

    }

    public Student changeLeadToStudent(String email) {

        Lead lead = leadRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Fail! -> Cause: Lead not found."));
        TrainingClass trainingClass = trainingClassRepository.findByName(lead.getaTrainingClassName()).
                orElseThrow(() -> new RuntimeException("Fail! -> Cause: Training class not found."));
        List<Position> positions = positionRepository.findAll();
        for (Position p: positions) {
            lead.removePosition(p);
            positionRepository.save(p);
        }
        Position position = positionRepository.findByRoleNameAndTeamName("ROLE_CLIENT","TEAM_STUDENT").
                orElseThrow(() -> new RuntimeException("Error: position not found!"));

        Set<Position> newPositionSet = new HashSet<>();
        newPositionSet.add(position);
        Student student = new Student();
        BeanUtils.copyProperties(lead,student);
        student.setTrainingClass(trainingClass);
        student.setAmountPaid(300);
        student.setRemainingBalance(trainingClass.getCourseFee()-300);
        student.setStatusAsOfDay(LocalDateTime.now().toString());
        student.setModifiedTime(LocalDateTime.now().toString());

//        Student student = new Student(lead.getFirstName(), lead.getLastName(), lead.getPhone(), lead.getEmail(),
//                trainingClass, PaymentPlan.OneTime.toString(), trainingClass.getCourseFee()-300);

        leadRepository.deleteByEmail(email);
        studentRepository.save(student);
        return student;

    }





}
