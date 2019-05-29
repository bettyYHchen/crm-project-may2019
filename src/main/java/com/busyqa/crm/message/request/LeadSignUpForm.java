package com.busyqa.crm.message.request;

import java.util.ArrayList;
import java.util.List;

public class LeadSignUpForm {

    private String firstName;
    private String lastName;
    private String password = "password";
    private String phone;
    private String email;
    private List<String> courseName;
    private List<String> batch;




    public LeadSignUpForm() {
    }

    public LeadSignUpForm(String firstName, String lastName, String password, String phone, String email, List<String> courseName, List<String> batch) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.courseName = courseName;
        this.batch = batch;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getCourseName() {
        return courseName;
    }

    public void setCourseName(List<String> courseName) {
        this.courseName = courseName;
    }

    public List<String> getBatch() {
        return batch;
    }

    public void setBatch(List<String> batch) {
        this.batch = batch;
    }
}
