package com.busyqa.crm.message.response;

public class StudentResponse {
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String paymentPlan;
    private String paymentPlanStatus;
    private String paymentPlanAgreement;
    private String aTrainingClassName;
    private String comment;
    private String createdTime;
    private String modifiedTime;
    private int amountPaid;
    private int remainingBalance;
    private boolean classFinished;

    public StudentResponse() {
    }

    public StudentResponse(String firstName, String lastName, String phone, String email, String paymentPlan, String paymentPlanStatus, String paymentPlanAgreement, String aTrainingClassName, String comment, String createdTime, String modifiedTime, int amountPaid, int remainingBalance, boolean classFinished) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.paymentPlan = paymentPlan;
        this.paymentPlanStatus = paymentPlanStatus;
        this.paymentPlanAgreement = paymentPlanAgreement;
        this.aTrainingClassName = aTrainingClassName;
        this.comment = comment;
        this.createdTime = createdTime;
        this.modifiedTime = modifiedTime;
        this.amountPaid = amountPaid;
        this.remainingBalance = remainingBalance;
        this.classFinished = classFinished;
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

    public String getPaymentPlanStatus() {
        return paymentPlanStatus;
    }

    public void setPaymentPlanStatus(String paymentPlanStatus) {
        this.paymentPlanStatus = paymentPlanStatus;
    }

    public String getPaymentPlanAgreement() {
        return paymentPlanAgreement;
    }

    public void setPaymentPlanAgreement(String paymentPlanAgreement) {
        this.paymentPlanAgreement = paymentPlanAgreement;
    }


    public String getaTrainingClassName() {
        return aTrainingClassName;
    }

    public void setaTrainingClassName(String aTrainingClassName) {
        this.aTrainingClassName = aTrainingClassName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(String modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public int getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(int amountPaid) {
        this.amountPaid = amountPaid;
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
}
