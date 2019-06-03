package com.busyqa.crm.model.user;

import com.busyqa.crm.model.academic.TrainingClass;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;


@Entity
@DiscriminatorValue("3")
public class Student extends User{

    private String phone;
    private String address;
    private String leadSource;
    private String leadStatus;
    private String comment;
    private String employmentStatus;
    private String currentJob;
    private String desiredJob;
    private String paymentPlan;
    private String paymentPlanStatus;
    private String paymentPlanAgreement;
    private String lastActivityTime;
    private String modifiedTime;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "training_class_id", referencedColumnName = "id")
    @JsonBackReference
    private TrainingClass trainingClass;

    private int amountPaid;
    private int remainingBalance;

    public Student() {
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

    public TrainingClass getTrainingClass() {
        return trainingClass;
    }

    public void setTrainingClass(TrainingClass trainingClass) {
        this.trainingClass = trainingClass;
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

    @JsonIgnore
    public boolean finishedClass() {
        return this.trainingClass.isFinished();
    }

    @JsonIgnore
    public int getClassFee() {
        return this.trainingClass.getCourseFee();
    }

    @JsonIgnore
    public void updateBalance() {
        this.setRemainingBalance(this.getClassFee()-this.getAmountPaid());
    }

    @Override
    public String toString() {
        return "Student{" +
                "phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", leadSource='" + leadSource + '\'' +
                ", leadStatus='" + leadStatus + '\'' +
                ", comment='" + comment + '\'' +
                ", employmentStatus='" + employmentStatus + '\'' +
                ", currentJob='" + currentJob + '\'' +
                ", desiredJob='" + desiredJob + '\'' +
                ", paymentPlan='" + paymentPlan + '\'' +
                ", paymentPlanStatus='" + paymentPlanStatus + '\'' +
                ", paymentPlanAgreement='" + paymentPlanAgreement + '\'' +
                ", lastActivityTime='" + lastActivityTime + '\'' +
                ", modifiedTime='" + modifiedTime + '\'' +
                ", trainingClass=" + trainingClass +
                ", amountPaid=" + amountPaid +
                ", remainingBalance=" + remainingBalance +
                '}';
    }


}
