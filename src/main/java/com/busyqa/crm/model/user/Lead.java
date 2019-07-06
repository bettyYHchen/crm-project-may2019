package com.busyqa.crm.model.user;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Set;

@Entity
@DiscriminatorValue("2")
public class Lead extends User{

    private String phone;
    private String address;
    private Boolean paidDeposit;
    private String leadSource;
    private String leadStatus;
    private String aTrainingClassName;
    private String comment;
    private String employmentStatus;
    private String currentJob;
    private String desiredJob;
    private String paymentPlan;
    private String paymentPlanStatus;
    private String paymentPlanAgreement;
    private String lastActivityTime;
    private String modifiedTime;
    private double discount;

    public Lead() {
    }



    public Lead(String name, String username, String email, String password, Set<Position> positions, String status, String statusAsOfDay, String phone, Boolean paidDeposit, String paymentPlan, String paymentPlanStatus, String paymentPlanAgreement, String leadSource, String leadStatus, String aTrainingClassName, String comment, String modifiedTime) {
        super(name, username, email, password, positions, status, statusAsOfDay);
        this.phone = phone;
        this.paidDeposit = paidDeposit;
        this.leadSource = leadSource;
        this.leadStatus = leadStatus;
        this.aTrainingClassName = aTrainingClassName;
        this.comment = comment;
        this.paymentPlan = paymentPlan;
        this.paymentPlanStatus = paymentPlanStatus;
        this.paymentPlanAgreement = paymentPlanAgreement;
        this.modifiedTime = modifiedTime;
    }

    public Lead(String name, String username, String email, String password, Set<Position> positions, String status, String statusAsOfDay, String phone, String address, Boolean paidDeposit, String leadSource, String leadStatus, String aTrainingClassName, String comment, String employmentStatus, String currentJob, String desiredJob, String paymentPlan, String paymentPlanStatus, String paymentPlanAgreement, String lastActivityTime, String modifiedTime) {
        super(name, username, email, password, positions, status, statusAsOfDay);
        this.phone = phone;
        this.address = address;
        this.paidDeposit = paidDeposit;
        this.leadSource = leadSource;
        this.leadStatus = leadStatus;
        this.aTrainingClassName = aTrainingClassName;
        this.comment = comment;
        this.employmentStatus = employmentStatus;
        this.currentJob = currentJob;
        this.desiredJob = desiredJob;
        this.paymentPlan = paymentPlan;
        this.paymentPlanStatus = paymentPlanStatus;
        this.paymentPlanAgreement = paymentPlanAgreement;
        this.lastActivityTime = lastActivityTime;
        this.modifiedTime = modifiedTime;
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

    public Boolean getPaidDeposit() {
        return paidDeposit;
    }

    public void setPaidDeposit(Boolean paidDeposit) {
        this.paidDeposit = paidDeposit;
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

    public String getPaymentPlanAgreement() {
        return paymentPlanAgreement;
    }

    public void setPaymentPlanAgreement(String paymentPlanAgreement) {
        this.paymentPlanAgreement = paymentPlanAgreement;
    }

    public String getPaymentPlanStatus() {
        return paymentPlanStatus;
    }

    public void setPaymentPlanStatus(String paymentPlanStatus) {
        this.paymentPlanStatus = paymentPlanStatus;
    }

    public String getLastActivityTime() {
        return lastActivityTime;
    }

    public void setLastActivityTime(String lastActivityTime) {
        this.lastActivityTime = lastActivityTime;
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



    @Override
    public String toString() {
        return "Lead{" +
                "phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", paidDeposit=" + paidDeposit +
                ", leadSource='" + leadSource + '\'' +
                ", leadStatus='" + leadStatus + '\'' +
                ", aTrainingClassName='" + aTrainingClassName + '\'' +
                ", comment='" + comment + '\'' +
                ", employmentStatus='" + employmentStatus + '\'' +
                ", currentJob='" + currentJob + '\'' +
                ", desiredJob='" + desiredJob + '\'' +
                ", paymentPlan='" + paymentPlan + '\'' +
                ", paymentPlanStatus='" + paymentPlanStatus + '\'' +
                ", paymentPlanAgreement='" + paymentPlanAgreement + '\'' +
                ", lastActivityTime='" + lastActivityTime + '\'' +
                ", modifiedTime='" + modifiedTime + '\'' +
                '}';
    }
}
