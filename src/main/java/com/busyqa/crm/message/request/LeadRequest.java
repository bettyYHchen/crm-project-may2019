package com.busyqa.crm.message.request;

public class LeadRequest {

    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private Boolean paidDeposit;
    private String paymentPlan;
    private String paymentPlanStatus;
    private String paymentPlanAgreement;
    private String leadSource;
    private String leadStatus;
    private String aTrainingClassName;
    private String comment;
    private String modifiedTime;
    private double discount;
    private Boolean dropOff;

    public LeadRequest() {
    }

    public LeadRequest(String firstName, String lastName, String phone, String email, boolean paidDeposit, String paymentMethod, String paymentPlanStatus, String paymentPlanAgreement, String leadSource, String leadStatus, String aTrainingClassName, String comment, String modifiedTime) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.paidDeposit = paidDeposit;
        this.paymentPlan = paymentMethod;
        this.paymentPlanStatus = paymentPlanStatus;
        this.paymentPlanAgreement = paymentPlanAgreement;
        this.leadSource = leadSource;
        this.leadStatus = leadStatus;
        this.aTrainingClassName = aTrainingClassName;
        this.comment = comment;
        this.modifiedTime = modifiedTime;
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

    public Boolean getPaidDeposit() {
        return paidDeposit;
    }

    public void setPaidDeposit(Boolean paidDeposit) {
        this.paidDeposit = paidDeposit;
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

    public String getLeadSource() {
        return leadSource;
    }

    public void setLeadSource(String leadSource) {
        this.leadSource = leadSource;
    }

    public String getLeadStatus() {
        return leadStatus;
    }

    public void setLeadStatus(String leadStatus) {
        this.leadStatus = leadStatus;
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

    public String getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(String modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Boolean getDropOff() {
        return dropOff;
    }

    public void setDropOff(Boolean dropOff) {
        this.dropOff = dropOff;
    }
}
