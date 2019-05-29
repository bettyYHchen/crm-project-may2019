package com.busyqa.crm.message.response;

public class StudentResponse {
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String paymentPlan;
    private int remainingBalance;
    private boolean classFinished;
    private String aTrainingClassName;

    public StudentResponse() {
    }

    public StudentResponse(String firstName, String lastName, String phone, String email, String paymentPlan, int remainingBalance, boolean classFinished, String aTrainingClassName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.paymentPlan = paymentPlan;
        this.remainingBalance = remainingBalance;
        this.classFinished = classFinished;
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

    public String getPaymentPlan() {
        return paymentPlan;
    }

    public void setPaymentPlan(String paymentPlan) {
        this.paymentPlan = paymentPlan;
    }

    public int getRemainingBalance() {
        return remainingBalance;
    }

    public void setRemainingBalance(int remainingBalance) {
        this.remainingBalance = remainingBalance;
    }

    public boolean isClassFinished() {
        return classFinished;
    }

    public void setClassFinished(boolean classFinished) {
        this.classFinished = classFinished;
    }

    public String getaTrainingClassName() {
        return aTrainingClassName;
    }

    public void setaTrainingClassName(String aTrainingClassName) {
        this.aTrainingClassName = aTrainingClassName;
    }
}
