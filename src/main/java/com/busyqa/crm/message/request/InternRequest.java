package com.busyqa.crm.message.request;

public class InternRequest {
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String coopStatus;
    private String aTrainingClassName;

    public InternRequest() {
    }

    public InternRequest(String firstName, String lastName, String phone, String email, String coopStatus, String aTrainingClassName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.coopStatus = coopStatus;
        this.aTrainingClassName = aTrainingClassName;
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

    public String getCoopStatus() {
        return coopStatus;
    }

    public void setCoopStatus(String coopStatus) {
        this.coopStatus = coopStatus;
    }

    public String getaTrainingClassName() {
        return aTrainingClassName;
    }

    public void setaTrainingClassName(String aTrainingClassName) {
        this.aTrainingClassName = aTrainingClassName;
    }
}
