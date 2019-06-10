package com.busyqa.crm.model.user;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Set;

@Entity
@DiscriminatorValue("6")
public class Mock extends User{
    private String phone;
    private String address;
    private String aTrainingClassName;
    private String employmentStatus;
    private String currentJob;
    private String desiredJob;
    private String lastActivityTime;
    private String modifiedTime;
    private String coopStartDate;
    private String coopEndDate;
    private String projectAssigned;
    private String performance;
    private String interviewDate;

    public Mock() {
    }

    public Mock(String name, String username, String email, String password, Set<Position> positions, String status, String statusAsOfDay, String phone, String address, String aTrainingClassName, String employmentStatus, String currentJob, String desiredJob, String lastActivityTime, String modifiedTime, String coopStartDate, String coopEndDate, String projectAssigned, String performance, String interviewDate) {
        super(name, username, email, password, positions, status, statusAsOfDay);
        this.phone = phone;
        this.address = address;
        this.aTrainingClassName = aTrainingClassName;
        this.employmentStatus = employmentStatus;
        this.currentJob = currentJob;
        this.desiredJob = desiredJob;
        this.lastActivityTime = lastActivityTime;
        this.modifiedTime = modifiedTime;
        this.coopStartDate = coopStartDate;
        this.coopEndDate = coopEndDate;
        this.projectAssigned = projectAssigned;
        this.performance = performance;
        this.interviewDate = interviewDate;
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

    public void setPerformance(String coopPerformance) {
        this.performance = coopPerformance;
    }

    public String getInterviewDate() {
        return interviewDate;
    }

    public void setInterviewDate(String interviewDate) {
        this.interviewDate = interviewDate;
    }
}
