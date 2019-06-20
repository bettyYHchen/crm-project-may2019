package com.busyqa.crm.controller;

import com.busyqa.crm.message.request.LeadRequest;
import com.busyqa.crm.message.response.LeadResponse;
import com.busyqa.crm.model.Mail;
import com.busyqa.crm.model.user.Lead;
import com.busyqa.crm.model.user.Student;
import com.busyqa.crm.services.LeadService;
import com.busyqa.crm.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.Errors;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("")
public class SalesController {

    @Autowired
    private MailService notificationService;

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

    @RequestMapping("/sendEmail/{email}")
    public String send(@PathVariable("email") String email) {

        /*
         * Here we will call sendEmail() for Sending mail to the sender.
         */
        try {
            notificationService.sendEmail(email);
        } catch (MailException mailException) {
            System.out.println(mailException);
        }
        return "Congratulations! Your email has been sent.";
    }

    @RequestMapping("/sendEmailWithAttachment/{email}")
    public String sendWithAttachment(@PathVariable("email") String email) throws MessagingException {

        /*
         * Here we will call sendEmailWithAttachment() for Sending mail to the sender
         * that contains a attachment.
         */
        try {
            notificationService.sendTemplatedEmailWithAttachment(email);
        } catch (MailException mailException) {
            System.out.println(mailException);
        }
        return "Congratulations! Your email with attachment has been sent.";
    }

    @PostMapping("/sendEmailWithTemplate/")
    public ResponseEntity<?> sendMailWithTemplate(@Valid @RequestBody Mail mail, Errors errors){
        if(errors.hasErrors()){
            return new ResponseEntity<>(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        return notificationService.sendPreparedMail(mail);
    }



}
