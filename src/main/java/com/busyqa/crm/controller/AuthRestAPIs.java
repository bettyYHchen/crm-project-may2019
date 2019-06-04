package com.busyqa.crm.controller;


import com.busyqa.crm.message.request.*;
import com.busyqa.crm.message.response.*;
import com.busyqa.crm.model.user.Lead;
import com.busyqa.crm.model.user.Employee;
import com.busyqa.crm.model.user.Position;
import com.busyqa.crm.repo.LeadRepository;
import com.busyqa.crm.repo.EmployeeRepository;
import com.busyqa.crm.repo.PositionRepository;
import com.busyqa.crm.repo.UserRepository;
import com.busyqa.crm.security.JwtProvider;
import com.busyqa.crm.services.ClientService;
import com.busyqa.crm.services.CrudService;
import com.busyqa.crm.services.LeadService;
import com.busyqa.crm.services.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PositionRepository positionRepository;


    @Autowired
    UserRepository userRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    LeadRepository leadRepository;

    @Autowired
    CrudService crudService;


    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    LeadService leadService;

    @Autowired
    ClientService clientService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return ResponseEntity.ok(
                new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<>(
                    new ResponseMessage("Fail -> Username is already taken!"), HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>(
                    new ResponseMessage("Fail -> Email is already in use!"), HttpStatus.BAD_REQUEST);
        }



        List<String> strPositions = signUpRequest.getPositions();
        List<Position> positionList = new ArrayList<>();
//        Boolean isEmployee = true;



        for (String r : strPositions) {
            String[] tmp = r.split(",");
            String roleName = tmp[0];
            if (roleName.equals("ROLE_CLIENT")) throw new RuntimeException("Wrong sign-up please use the client sign-up!");
            String teamName = tmp[1];
            System.out.println(roleName);
            System.out.println(roleName.length());
            System.out.println(teamName);
            System.out.println(teamName.length());
            Position position = positionRepository.findByRoleNameAndTeamName(roleName,teamName)
                    .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Position not find."));
            positionList.add(position);
        }
        Set<Position> positionSet = positionList.stream().collect(Collectors.toSet());

        Employee employee = new Employee(
                signUpRequest.getName(),
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()),
                positionSet,"YES", LocalDateTime.now().toString(),1);
        employeeRepository.save(employee);
        UserPrinciple userPrinciple = UserPrinciple.build(employee);
        System.out.println(userPrinciple);

        return new ResponseEntity<>(
                new ResponseMessage("User registered successfully!"), HttpStatus.OK);



    }

    // for the use of an employee
    @PostMapping("/signupLead")
    public LeadSignUpForm registerLead(@Valid @RequestBody LeadSignUpForm leadSignUpForm) {
        String name = leadSignUpForm.getFirstName() + " " + leadSignUpForm.getLastName();

        if (userRepository.existsByEmail(leadSignUpForm.getEmail())) throw
            new RuntimeException("Error: email in used!");




        List<Position> positionList = new ArrayList<>();
        Position position = positionRepository.findByRoleNameAndTeamName("ROLE_CLIENT","TEAM_LEAD")
                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: Position CLIENT LEAD not find."));
        positionList.add(position);
        Set<Position> positionSet = positionList.stream().collect(Collectors.toSet());

        String aTrainingClassName = leadSignUpForm.getCourseName().get(0) + " " + leadSignUpForm.getBatch().get(0);

        Lead lead = new Lead(
                name,
                leadSignUpForm.getEmail(),
                leadSignUpForm.getEmail(),
                encoder.encode(leadSignUpForm.getPassword()),
                positionSet,"YES", LocalDateTime.now().toString(),
                leadSignUpForm.getPhone(),
                leadSignUpForm.isPaidDeposit(),
                leadSignUpForm.getPaymentPlan(),
                leadSignUpForm.getPaymentPlanStatus(),
                leadSignUpForm.getPaymentPlanAgreement(),
                leadSignUpForm.getLeadSource(),
                leadSignUpForm.getLeadStatus(),
                aTrainingClassName,
                leadSignUpForm.getComment(),
                LocalDateTime.now().toString()
                );
        leadRepository.save(lead);
        UserPrinciple userPrinciple = UserPrinciple.build(lead);

        return leadSignUpForm;



    }

    // for the use of a lead to set up his/her profile




    // reset your passoword
    @PutMapping("/resetpassword/{username}")
    public ApiResponse<UserResponse> resetPassword(@PathVariable String username, @RequestBody LoginForm loginForm) {
        return new ApiResponse<>(HttpStatus.OK.value(), "Password reset successfully.",
                crudService.resetPassword(username, loginForm));
    }

    // Get Your personal info
    @GetMapping("/myInfo/{username}")
    public ApiResponse<UserResponse> getOne(@PathVariable String username){
        return new ApiResponse<>(HttpStatus.OK.value(), "User fetched successfully.",crudService.getByUsername(username));
    }



    // The lead register page to update lead info after the portal link of resetting password
    @GetMapping("changeLeadInfo/{email}")
    public LeadResponse getLead(@PathVariable("email") String email) {
        return this.leadService.listLeadByEmail(email);

    }

    @PutMapping("changeLeadInfo/{email}")
    public ResponseEntity<ClientResponse> updateLeadInfo(@PathVariable("email") String email, @RequestBody LeadClientRequest leadClientRequest) {
        return this.clientService.updateLeadInfo(email, leadClientRequest);

    }

    // The client page to update client info by himself
    @GetMapping("changeClientInfo/{email}")
    public ClientResponse getClient(@PathVariable("email") String email) {
        return this.clientService.listClientByEmail(email);

    }


    @PutMapping("changeClientInfo/{email}")
    public ResponseEntity<ClientResponse> updateClientInfo(@PathVariable("email") String email, @RequestBody ClientRequest clientRequest) {
        return this.clientService.updateClientInfo(email, clientRequest);

    }








}

