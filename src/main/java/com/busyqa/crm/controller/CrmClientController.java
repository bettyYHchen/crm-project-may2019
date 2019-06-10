package com.busyqa.crm.controller;

import com.busyqa.crm.message.request.AlumniRequest;
import com.busyqa.crm.message.request.LeadRequest;
import com.busyqa.crm.message.request.MockRequest;
import com.busyqa.crm.message.request.ResumeRequest;
import com.busyqa.crm.message.response.LeadResponse;
import com.busyqa.crm.model.Mail;
import com.busyqa.crm.model.user.Alumni;
import com.busyqa.crm.model.user.Mock;
import com.busyqa.crm.model.user.Resume;
import com.busyqa.crm.model.user.Student;
import com.busyqa.crm.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("")
public class CrmClientController {

    @Autowired
    ResumeService resumeService;

    @Autowired
    MockService mockService;

    @Autowired
    AlumniService alumniService;


    // User Resume

    @GetMapping("/allTeams/resumes")
    public List<Resume> listResumes(){
        return this.resumeService.listResumes();
    }

    @GetMapping("/allTeams/resumes/{email}")
    public Resume getResume(@PathVariable("email") String email) {
        return resumeService.listResumeByEmail(email);
    }


    @PutMapping("/allTeams/resumes/{email}")
    public ResponseEntity<Resume> updateResume(@PathVariable("email") String email, @RequestBody ResumeRequest resumeRequest) {
        return resumeService.updateResume(email, resumeRequest);
    }

    @DeleteMapping("/allTeams/resumes/{email}")
    public  ResponseEntity<?> deleteResume(@PathVariable("email") String email) {
        return resumeService.deleteResume(email);
    }

    @DeleteMapping("/allTeams/changeResumeToMock/{email}")
    public Mock changeToMock(@PathVariable("email") String email) {
        return resumeService.changeResumeToMock(email);
    }

    // User Mock

    @GetMapping("/allTeams/mocks")
    public List<Mock> listMocks(){
        return this.mockService.listMocks();
    }

    @GetMapping("/allTeams/mocks/{email}")
    public Mock getMock(@PathVariable("email") String email) {
        return mockService.listMockByEmail(email);
    }


    @PutMapping("/allTeams/mocks/{email}")
    public ResponseEntity<Mock> updateMock(@PathVariable("email") String email, @RequestBody MockRequest mockRequest) {
        return mockService.updateMock(email, mockRequest);
    }

    @DeleteMapping("/allTeams/mocks/{email}")
    public  ResponseEntity<?> deleteMock(@PathVariable("email") String email) {
        return mockService.deleteMock(email);
    }

    @DeleteMapping("/allTeams/changeMockToAlumni/{email}")
    public Alumni changeToAlumni(@PathVariable("email") String email) {
        return mockService.changeMockToAlumni(email);
    }

    // Alumni

    @GetMapping("/allTeams/alumnus")
    public List<Alumni> listAlumnus(){
        return this.alumniService.listAlumnus();
    }

    @GetMapping("/allTeams/alumnus/{email}")
    public Alumni getAlumni(@PathVariable("email") String email) {
        return alumniService.listAlumniByEmail(email);
    }


    @PutMapping("/allTeams/alumnus/{email}")
    public ResponseEntity<Alumni> updateAlumni(@PathVariable("email") String email, @RequestBody AlumniRequest alumniRequest) {
        return alumniService.updateAlumni(email, alumniRequest);
    }

    @DeleteMapping("/allTeams/alumnus/{email}")
    public  ResponseEntity<?> deleteAlumni(@PathVariable("email") String email) {
        return alumniService.deleteAlumni(email);
    }


}
