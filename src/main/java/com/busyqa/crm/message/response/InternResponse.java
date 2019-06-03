package com.busyqa.crm.message.response;

public class InternResponse {
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String paymentPlan;
    private String paymentPlanStatus;
    private String paymentPlanAgreement;
    private String aTrainingClassName;
    private String createdTime;
    private String modifiedTime;
    private int amountPaid;
    private int remainingBalance;
    private String coopStatus;
    private String coopStartDate;
    private String coopEndDate;
    private String projectAssigned;
    private String performance;

    public InternResponse() {
    }

    public InternResponse(String firstName, String lastName, String phone, String email, String paymentPlan, String paymentPlanStatus, String paymentPlanAgreement, String aTrainingClassName, String createdTime, String modifiedTime, int amountPaid, int remainingBalance, String coopStatus, String coopStartDate, String coopEndDate, String projectAssigned, String performance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.paymentPlan = paymentPlan;
        this.paymentPlanStatus = paymentPlanStatus;
        this.paymentPlanAgreement = paymentPlanAgreement;
        this.aTrainingClassName = aTrainingClassName;
        this.createdTime = createdTime;
        this.modifiedTime = modifiedTime;
        this.amountPaid = amountPaid;
        this.remainingBalance = remainingBalance;
        this.coopStatus = coopStatus;
        this.coopStartDate = coopStartDate;
        this.coopEndDate = coopEndDate;
        this.projectAssigned = projectAssigned;
        this.performance = performance;
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

    public String getCoopStatus() {
        return coopStatus;
    }

    public void setCoopStatus(String coopStatus) {
        this.coopStatus = coopStatus;
    }

    public String getCoopStartDate() {
        return coopStartDate;
    }

    public void setCoopStartDate(String coopStartDate) {
        this.coopStartDate = coopStartDate;
    }

    public String getCoopEndDate() {
        return coopEndDate;
    }

    public void setCoopEndDate(String coopEndDate) {
        this.coopEndDate = coopEndDate;
    }

    public String getProjectAssigned() {
        return projectAssigned;
    }

    public void setProjectAssigned(String projectAssigned) {
        this.projectAssigned = projectAssigned;
    }

    public String getPerformance() {
        return performance;
    }

    public void setPerformance(String performance) {
        this.performance = performance;
    }
}
