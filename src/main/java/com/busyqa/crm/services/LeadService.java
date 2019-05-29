package com.busyqa.crm.services;


import com.busyqa.crm.message.request.LeadRequest;
import com.busyqa.crm.message.response.LeadResponse;
import com.busyqa.crm.model.user.Lead;
import com.busyqa.crm.model.user.Position;
import com.busyqa.crm.model.user.Student;
import com.busyqa.crm.model.academic.PaymentPlan;
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
            leadResponses.add(new LeadResponse(firstName,lastName,
                    l.getPhone(),l.getEmail(),l.getPaidDeposite(),l.getaTrainingClassName()));

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
                lead.getPhone(),lead.getEmail(),lead.getPaidDeposite(),lead.getaTrainingClassName());
    }

//        return this.leadRepository.getOne(id);}



    public ResponseEntity<LeadResponse> updateLead(String email, LeadRequest leadRequest) {

        return leadRepository.findByEmail(email).map(recordUpdated -> {
            String name = leadRequest.getFirstName() + " " + leadRequest.getLastName();
            recordUpdated.setName(name);
            recordUpdated.setUsername(leadRequest.getEmail());
            recordUpdated.setEmail(leadRequest.getEmail());
            recordUpdated.setPhone(leadRequest.getPhone());
            recordUpdated.setaTrainingClassName(leadRequest.getaTrainingClassName());
            recordUpdated.setPaidDeposite(leadRequest.isPaidDeposite());
            recordUpdated.setStatusAsOfDay(LocalDateTime.now().toString());
            this.leadRepository.save(recordUpdated);
            LeadResponse leadResponse = new LeadResponse();
            BeanUtils.copyProperties(leadRequest,leadResponse);
            return ResponseEntity.ok().body(leadResponse);
                 }).orElse(ResponseEntity.notFound().build());



//        return leadRepository.findById(id).map(recordUpdated -> {
//            recordUpdated.setFirstName(lead.getFirstName());
//            recordUpdated.setLastName(lead.getLastName());
//            recordUpdated.setEmail(lead.getEmail());
//            recordUpdated.setPhone(lead.getPhone());
//            recordUpdated.setaTrainingClassName(lead.getaTrainingClassName());
//            recordUpdated.setPaidDeposite(lead.getPaidDeposite());
//            this.leadRepository.save(recordUpdated);
//            return ResponseEntity.ok().body(recordUpdated);
//        }).orElse(ResponseEntity.notFound().build());


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
        Student student = new Student(lead.getName(),lead.getUsername(),lead.getEmail(),lead.getPassword(), newPositionSet,
                lead.getStatus(),lead.getStatusAsOfDay(),lead.getPhone(),trainingClass,PaymentPlan.OneTime.toString(),
                trainingClass.getCourseFee()-300);


//        Student student = new Student(lead.getFirstName(), lead.getLastName(), lead.getPhone(), lead.getEmail(),
//                trainingClass, PaymentPlan.OneTime.toString(), trainingClass.getCourseFee()-300);

        leadRepository.deleteByEmail(email);
        studentRepository.save(student);
        return student;

    }






}
