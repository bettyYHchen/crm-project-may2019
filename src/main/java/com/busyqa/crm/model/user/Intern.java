package com.busyqa.crm.model.user;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@DiscriminatorValue("4")
public class Intern extends User{

    private String phone;
    private String address;
    private String leadSource;
    private String leadStatus;
    private String aTrainingClassName;
    private String employmentStatus;
    private String currentJob;
    private String desiredJob;
    private String paymentPlan;
    private String paymentPlanStatus;
    private String paymentPlanAgreement;
    private String lastActivityTime;
    private String modifiedTime;
    private int amountPaid;
    private int remainingBalance;
    private String coopStatus;
    private String coopStartDate;
    private String coopEndDate;
    private String projectAssigned;
    private String performance;

    public Intern() {
    }

    public Intern(String name, String username, String email, String password, Set<Position> positions, String status, String statusAsOfDay, String phone, String address, String leadSource, String leadStatus, String aTrainingClassName, String employmentStatus, String currentJob, String desiredJob, String paymentPlan, String paymentPlanStatus, String paymentPlanAgreement, String lastActivityTime, String modifiedTime, int amountPaid, int remainingBalance, String coopStatus, String coopStartDate, String coopEndDate, String projectAssigned, String performance) {
        super(name, username, email, password, positions, status, statusAsOfDay);
        this.phone = phone;
        this.address = address;
        this.leadSource = leadSource;
        this.leadStatus = leadStatus;
        this.aTrainingClassName = aTrainingClassName;
        this.employmentStatus = employmentStatus;
        this.currentJob = currentJob;
        this.desiredJob = desiredJob;
        this.paymentPlan = paymentPlan;
        this.paymentPlanStatus = paymentPlanStatus;
        this.paymentPlanAgreement = paymentPlanAgreement;
        this.lastActivityTime = lastActivityTime;
        this.modifiedTime = modifiedTime;
        this.amountPaid = amountPaid;
        this.remainingBalance = remainingBalance;
        this.coopStatus = coopStatus;
        this.coopStartDate = coopStartDate;
        this.coopEndDate = coopEndDate;
        this.projectAssigned = projectAssigned;
        this.performance = performance;
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
