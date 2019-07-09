
package com.busyqa.crm.services;

import com.busyqa.crm.message.request.LoginForm;
import com.busyqa.crm.message.request.SignUpForm;
import com.busyqa.crm.message.request.UserRequest;
import com.busyqa.crm.message.response.UserResponse;
import com.busyqa.crm.model.user.*;
import com.busyqa.crm.repo.EmployeeRepository;
import com.busyqa.crm.repo.PositionRepository;
import com.busyqa.crm.repo.UserRepository;
import com.busyqa.crm.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class CrudService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PositionRepository positionRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    private EmployeeRepository employeeRepository;



    public UserResponse getUserByUserName(String username) {
        Employee user = employeeRepository.findByUsername(username).
                orElseThrow(() -> new RuntimeException("Fail! -> Cause: User not find."));
        List<String> strPositions  = user.getRolesTeams();
        return new UserResponse(user.getName(),user.getUsername(),
                user.getEmail(),strPositions,user.getStatus(),user.getStatusAsOfDay());

    }

    public UserResponse getUserByUserId(Long id) {
        Employee user = employeeRepository.getOne(id);
        List<String> strPositions  = user.getRolesTeams();
        return new UserResponse(user.getName(),user.getUsername(),
                user.getEmail(),strPositions,user.getStatus(),user.getStatusAsOfDay());

    }

    public List<UserResponse> getUsers() {
        List<Employee> users = new ArrayList<>();
        List<Employee> usersToAdd = employeeRepository.findByPositions_RoleName("ROLE_USER");
        if (!usersToAdd.isEmpty()) {
            users= new Common().mergeTwoList(users,usersToAdd);
        }

        List<UserResponse> userResponses = new ArrayList<>();

        for (Employee u: users) {
            List<String> strPositions = u.getPositions().stream().map(position -> (position.getRoleName()+","+position.getTeamName()))
                    .collect(Collectors.toList());
            userResponses.add(new UserResponse(u.getName(),u.getUsername(),
                    u.getEmail(),strPositions,u.getStatus(),u.getStatusAsOfDay()));
        }

        return userResponses;

    }


    public List<UserResponse> getlist(List<String> strTeams) {

        List<Employee> users = new ArrayList<>();
        List<Employee> usersToAdd;
        if (strTeams.contains("TEAM_ADMIN")) {
            users = employeeRepository.findAllWithPositions();
        }else{
            // get all users of the login user's team only
            for (String strTeam: strTeams) {
                usersToAdd = employeeRepository.findByPositions_TeamName(strTeam);
                for (Employee u : usersToAdd){
                    if (!users.contains(u))
                        users.add(u);
                }
            }
        }


        List<UserResponse> userResponses = new ArrayList<>();

        for (Employee u: users) {
            List<String> strPositions = u.getPositions().stream().map(position -> (position.getRoleName()+","+position.getTeamName()))
                    .collect(Collectors.toList());
            userResponses.add(new UserResponse(u.getName(),u.getUsername(),
                    u.getEmail(),strPositions,u.getStatus(),u.getStatusAsOfDay()));
        }

        return userResponses;
    }

    public boolean resetPassword(String username, LoginForm loginForm) {
        System.out.println("the username is " + username);
        Employee user = employeeRepository.findByUsername(username).
                orElseThrow(() -> new RuntimeException("Fail! -> Cause: User not found."));
        user.setPassword(encoder.encode(loginForm.getPassword()));
        employeeRepository.save(user);
        return true;

    }



    public UserResponse getByUsername(String username) {

        Employee user = employeeRepository.findByUsername(username).orElseThrow(
                () -> new RuntimeException("Error: User not found!"));

        List<String> strPositions  = user.getRolesTeams();
        UserResponse userResponse = new UserResponse(user.getName(),user.getUsername(),
                user.getEmail(),strPositions,user.getStatus(),user.getStatusAsOfDay());
        return userResponse;

    }

    public UserResponse update(String username, UserRequest userRequest) {
        Employee user = employeeRepository.findByUsername(username).orElseThrow(
                () -> new RuntimeException("Error: User not found!"));
        List<String> positionsGet = userRequest.getPositions();


        user.setName(userRequest.getName());
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setStatus(userRequest.getStatus());
        user.setStatusAsOfDay(LocalDateTime.now().toString());


        List<Position> positions = positionRepository.findAll();
        for (Position p: positions) {
            user.removePosition(p);
            positionRepository.save(p);
        }

        // add all positions obtained from put request
        for (String p: positionsGet) {
            String[] tmp = p.split(",");
            String strRole = tmp[0];
            String strTeam = tmp[1];
            Position position = positionRepository.findByRoleNameAndTeamName(strRole,strTeam)
                    .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Position not find."));
            user.addPosition(position);
            positionRepository.save(position);
        }


        employeeRepository.save(user);


        return new UserResponse(user.getName(),user.getUsername(),
                user.getEmail(),positionsGet,user.getStatus(), LocalDateTime.now().toString());


    }



    public void delete (String username) {
        employeeRepository.deleteByUsername(username);
    }



}
