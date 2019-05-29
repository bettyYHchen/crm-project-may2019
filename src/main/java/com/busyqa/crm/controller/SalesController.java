package com.busyqa.crm.controller;

import com.busyqa.crm.message.request.LeadRequest;
import com.busyqa.crm.message.response.LeadResponse;
import com.busyqa.crm.model.user.Lead;
import com.busyqa.crm.model.user.Student;
import com.busyqa.crm.services.LeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("")
public class SalesController {

    @Autowired
    private LeadService leadService;


    @GetMapping("/pm/leads")
    @PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
    public List<LeadResponse> listLeads(){
        return this.leadService.listLeads();
    }

    @GetMapping("/pm/leads/{email}")
    @PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
    public LeadResponse getLead(@PathVariable("email") String email) {
        return this.leadService.listLeadByEmail(email);

    }


    @PutMapping("/pm/leads/{email}")
    @PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
    public ResponseEntity<LeadResponse> updateLead(@PathVariable("email") String email, @RequestBody LeadRequest leadRequest) {
        return leadService.updateLead(email,leadRequest);

    }

    @DeleteMapping("/pm/leads/{email}")
    @PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
    public  ResponseEntity<?> deleteLead(@PathVariable("email") String email) {
        return leadService.deleteLead(email);
    }

    @DeleteMapping("/pm/changeLeadToStudent/{email}")
    @PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
    public Student changeToStudent(@PathVariable("email") String email) {
        return leadService.changeLeadToStudent(email);
    }




}
