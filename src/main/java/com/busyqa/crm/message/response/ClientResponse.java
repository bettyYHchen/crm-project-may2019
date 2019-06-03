package com.busyqa.crm.message.response;

public class ClientResponse {

    private String name; // cannot be modified by client
    private String email; // cannot be modified by client
    private String phone;
    private String address;
    private String aTrainingClassName; // cannot be modified by client
    private String employmentStatus;
    private String currentJob;
    private String desiredJob;
    private String paymentPlan; // cannot be modified by client
    private String remainingFee;


    public ClientResponse() {
    }

    public ClientResponse(String name, String email, String phone, String address, String aTrainingClassName, String employmentStatus, String currentJob, String desiredJob, String paymentPlan, String remainingFee) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.aTrainingClassName = aTrainingClassName;
        this.employmentStatus = employmentStatus;
        this.currentJob = currentJob;
        this.desiredJob = desiredJob;
        this.paymentPlan = paymentPlan;
        this.remainingFee = remainingFee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getaTrainingClassName() {
        return aTrainingClassName;
    }

    public void setaTrainingClassName(String aTrainingClassName) {
        this.aTrainingClassName = aTrainingClassName;
    }

    public String getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(String employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    public String getCurrentJob() {
        return currentJob;
    }

    public void setCurrentJob(String currentJob) {
        this.currentJob = currentJob;
    }

    public String getDesiredJob() {
        return desiredJob;
    }

    public void setDesiredJob(String desiredJob) {
        this.desiredJob = desiredJob;
    }


    public String getPaymentPlan() {
        return paymentPlan;
    }

    public void setPaymentPlan(String paymentPlan) {
        this.paymentPlan = paymentPlan;
    }

    public String getRemainingFee() {
        return remainingFee;
    }

    public void setRemainingFee(String remainingFee) {
        this.remainingFee = remainingFee;
    }
}
