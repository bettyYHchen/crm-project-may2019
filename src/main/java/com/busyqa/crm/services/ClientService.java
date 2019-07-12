package com.busyqa.crm.services;

import com.busyqa.crm.message.request.ClientRequest;
import com.busyqa.crm.message.request.LeadClientRequest;
import com.busyqa.crm.message.response.ClientResponse;
import com.busyqa.crm.model.user.*;
import com.busyqa.crm.model.user.payment.Payment;
import com.busyqa.crm.repo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    LeadRepository leadRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    InternRepository internRepository;

    @Autowired
    ResumeRepository resumeRepository;

    @Autowired
    MockRepository mockRepository;

    @Autowired
    PaymentRepository paymentRepository;

    public List<User> getDropOffUsers() {
        return userRepository.findAllDropOff();
    }

    public ClientResponse listClientByEmail(String email) {
        // We are not sure whether the login client is lead, student, or an intern
        Lead lead = leadRepository.findByEmail(email).orElse(null);
        Student student = studentRepository.findByEmail(email).orElse(null);
        Intern intern = internRepository.findByEmail(email).orElse(null);
        Resume resume = resumeRepository.findByEmail(email).orElse(null);
        Mock mock = mockRepository.findByEmail(email).orElse(null);
        ClientResponse clientResponse = new ClientResponse();
        if (null != lead) {
            BeanUtils.copyProperties(lead,clientResponse);
        }
        else if (null != student) {
            BeanUtils.copyProperties(student,clientResponse);
            clientResponse.setaTrainingClassName(student.getTrainingClass().getName());

        }
        else if (null != intern) {
            BeanUtils.copyProperties(intern,clientResponse);
        }
        else if (null != resume) {
            BeanUtils.copyProperties(resume,clientResponse);
        }
        else if (null != mock) {
            BeanUtils.copyProperties(mock,clientResponse);
        }
        else throw new RuntimeException("Cannot find a Client with provided email!");
        return clientResponse;

    }

    public ResponseEntity<ClientResponse> updateLeadInfo(String email, LeadClientRequest leadClientRequest) {
        return leadRepository.findByEmail(email).map(recordUpdated -> {
            recordUpdated.setName(leadClientRequest.getName());
            recordUpdated.setPhone(leadClientRequest.getPhone());
            recordUpdated.setAddress(leadClientRequest.getCity() + "," + leadClientRequest.getState()
                    + "," + leadClientRequest.getCountry());
            recordUpdated.setPaymentPlan(leadClientRequest.getPaymentPlan());
            recordUpdated.setPaymentPlanAgreement(leadClientRequest.getPaymentPlanAgreement());
            recordUpdated.setLeadSource(leadClientRequest.getLeadSource());
            recordUpdated.setaTrainingClassName(leadClientRequest.getaTrainingClassName());
            recordUpdated.setEmploymentStatus(leadClientRequest.getEmploymentStatus());
            recordUpdated.setCurrentJob(leadClientRequest.getCurrentJob());
            recordUpdated.setDesiredJob(leadClientRequest.getDesiredJob());
            recordUpdated.setModifiedTime(LocalDateTime.now().toString());
            this.leadRepository.save(recordUpdated);
            ClientResponse clientRequest = new ClientResponse();
            BeanUtils.copyProperties(leadClientRequest,clientRequest);
            return ResponseEntity.ok().body(clientRequest);
        }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<ClientResponse> updateClientInfo(String email, ClientRequest clientRequest) {

        Lead lead = leadRepository.findByEmail(email).orElse(null);
        Student student = studentRepository.findByEmail(email).orElse(null);
        Intern intern = internRepository.findByEmail(email).orElse(null);
        ClientResponse clientResponse = new ClientResponse();
        if (!lead.equals(null)) {
            lead.setAddress(clientRequest.getAddress());
            lead.setEmploymentStatus(clientRequest.getEmploymentStatus());
            lead.setCurrentJob(clientRequest.getCurrentJob());
            lead.setDesiredJob(clientRequest.getDesiredJob());
            this.leadRepository.save(lead);
            BeanUtils.copyProperties(lead,clientResponse);
        }
        else if (!student.equals(null)) {
            student.setAddress(clientRequest.getAddress());
            student.setEmploymentStatus(clientRequest.getEmploymentStatus());
            student.setCurrentJob(clientRequest.getCurrentJob());
            student.setDesiredJob(clientRequest.getDesiredJob());
            this.studentRepository.save(student);
            BeanUtils.copyProperties(student,clientResponse);
            clientResponse.setaTrainingClassName(student.getTrainingClass().getName());

        }
        else if (!intern.equals(null)) {
            intern.setAddress(clientRequest.getAddress());
            intern.setEmploymentStatus(clientRequest.getEmploymentStatus());
            intern.setCurrentJob(clientRequest.getCurrentJob());
            intern.setDesiredJob(clientRequest.getDesiredJob());
            this.internRepository.save(intern);
            BeanUtils.copyProperties(intern,clientResponse);
        }
        else throw new RuntimeException("Cannot find a Client with provided email!");
        return ResponseEntity.ok().body(clientResponse);

    }

}
