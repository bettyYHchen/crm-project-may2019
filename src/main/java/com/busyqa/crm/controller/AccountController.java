package com.busyqa.crm.controller;

import com.busyqa.crm.message.request.InternRequest;
import com.busyqa.crm.message.request.StudentRequest;
import com.busyqa.crm.message.response.InternResponse;
import com.busyqa.crm.message.response.StudentResponse;
import com.busyqa.crm.model.user.Intern;
import com.busyqa.crm.model.user.Resume;
import com.busyqa.crm.services.InternService;
import com.busyqa.crm.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("")
public class AccountController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private InternService internService;


    @GetMapping("/user/students")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<StudentResponse> listStudents(){
        return this.studentService.listStudents();
    }

    @GetMapping("/user/students/{email}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public StudentResponse getStudent(@PathVariable("email") String email) {
        return this.studentService.listStudentByEmail(email);

    }


    @PutMapping("/user/students/{email}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<StudentResponse> updateStudent(@PathVariable("email") String email, @RequestBody StudentRequest studentRequest) {
        return studentService.updateStudent(email,studentRequest);

    }

    @DeleteMapping("/user/students/{email}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public  ResponseEntity<?> deleteStudent(@PathVariable("email") String email) {
        return studentService.deleteStudent(email);
    }

    @DeleteMapping("/user/changeStudentToIntern/{email}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Intern changeToIntern(@PathVariable("email") String email) {
        return studentService.changeStudentToIntern(email);
    }

    @GetMapping("/user/interns")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<InternResponse> listInterns(){
        return this.internService.listInterns();
    }

    @GetMapping("/user/interns/{email}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public InternResponse getIntern(@PathVariable("email") String email) {
        return this.internService.listInternByEmail(email);

    }


    @PutMapping("/user/interns/{email}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<InternResponse> updateIntern(@PathVariable("email") String email, @RequestBody InternRequest internRequest) {
        return internService.updateIntern(email,internRequest);

    }

    @DeleteMapping("/user/interns/{email}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public  ResponseEntity<?> deleteIntern(@PathVariable("email") String email) {
        return internService.deleteIntern(email);
    }

    @DeleteMapping("/user/changeInternToResume/{email}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Resume changeToResume(@PathVariable("email") String email) {
        return internService.changeInternToResume(email);
    }




}
