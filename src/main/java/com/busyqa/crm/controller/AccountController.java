package com.busyqa.crm.controller;

import com.busyqa.crm.exception.ResourceNotFoundException;
import com.busyqa.crm.message.request.InternRequest;
import com.busyqa.crm.message.request.PaymentRequest;
import com.busyqa.crm.message.request.StudentRequest;
import com.busyqa.crm.message.response.InternResponse;
import com.busyqa.crm.message.response.StudentResponse;
import com.busyqa.crm.model.user.Intern;
import com.busyqa.crm.model.user.Resume;
import com.busyqa.crm.model.user.payment.Payment;
import com.busyqa.crm.repo.PaymentRepository;
import com.busyqa.crm.repo.UserRepository;
import com.busyqa.crm.services.ClientService;
import com.busyqa.crm.services.InternService;
import com.busyqa.crm.services.PaymentService;
import com.busyqa.crm.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("")
public class AccountController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private InternService internService;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PaymentService paymentService;


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

    // payment history
    @GetMapping("/users/{userId}/payments")
    public List<Payment> getAllPaymentsByUserId(@PathVariable (value = "userId") Long userId) {
        Pageable pageable = PageRequest.of(0, 30, Sort.by("date"));
        Page<Payment> paymentsPage = paymentRepository.findByUserId(userId,pageable);
        return paymentsPage.getContent();
    }

    @GetMapping("/users/{userId}/payments/{paymentId}")
    public Payment getPaymentByUserIdAndPaymentId(@PathVariable (value = "userId") Long userId,
                                                  @PathVariable (value = "paymentId") Long paymentId) {
        Payment payment = paymentRepository.findByIdAndUserId(paymentId, userId).orElseThrow(
                () -> new RuntimeException("Error: Payment not found!"));
        return payment;
    }
//
    @PostMapping("/users/{userId}/payments")
    public Payment createPayment(@PathVariable (value = "userId") Long userId,
                                 @Valid @RequestBody PaymentRequest paymentRequest) {
        return userRepository.findById(userId).map(user -> {
            Payment payment = new Payment();
            payment.setDate(paymentRequest.getDate());
            payment.setAmount(paymentRequest.getAmount());
            payment.setLateFee(paymentRequest.getLateFee());
            payment.setStatus(paymentRequest.getStatus());
            payment.setUser(user);
            return paymentRepository.save(payment);
        }).orElseThrow(() -> new ResourceNotFoundException("UserId " + userId + " not found"));
    }
//
    @PutMapping("/users/{userId}/payments/{paymentId}")
    public Payment updatePayment(@PathVariable (value = "userId") Long userId,
                                 @PathVariable (value = "paymentId") Long paymentId,
                                 @Valid @RequestBody PaymentRequest paymentRequest) {
        if(!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("UserId " + userId + " not found");
        }

        return paymentRepository.findById(paymentId).map(payment -> {
            payment.setDate(paymentRequest.getDate());
            payment.setAmount(paymentRequest.getAmount());
            payment.setLateFee(paymentRequest.getLateFee());
            payment.setStatus(paymentRequest.getStatus());
            return paymentRepository.save(payment);
        }).orElseThrow(() -> new ResourceNotFoundException("PaymentId " + paymentId + "not found"));
    }
//
    @DeleteMapping("/users/{userId}/payments/{paymentId}")
    public ResponseEntity<?> deletePayment(@PathVariable (value = "userId") Long userId,
                                           @PathVariable (value = "paymentId") Long paymentId) {
        return paymentRepository.findByIdAndUserId(paymentId, userId).map(payment -> {
            paymentRepository.delete(payment);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Payment not found with id " + paymentId + " and userId " + userId));
    }

    @GetMapping("/users/{userId}/className/{className}")
    public List<Payment> fetchAndUpdatePayments(@PathVariable (value = "userId") Long userId,
                                                  @PathVariable (value = "className") String className) {
        return paymentService.updatePayments(userId,className);
    }


}
