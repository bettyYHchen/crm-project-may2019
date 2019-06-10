package com.busyqa.crm.model.user;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Set;

@Entity
@DiscriminatorValue("5")
public class Resume extends User{
    private String phone;
    private String address;
    private String aTrainingClassName;
    private String employmentStatus;
    private String currentJob;
    private String desiredJob;
    private String lastActivityTime;
    private String modifiedTime;
    private String projectAssigned;
    private String performance;
    private String resumeStartDate;
    private String resumeEndDate;
    private String resumeDoc;

    public Resume() {
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

    public String getResumeStartDate() {
        return resumeStartDate;
    }

    public void setResumeStartDate(String coopStartDate) {
        this.resumeStartDate = coopStartDate;
    }

    public String getResumeEndDate() {
        return resumeEndDate;
    }

    public void setResumeEndDate(String coopEndDate) {
        this.resumeEndDate = coopEndDate;
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

    public void setPerformance(String ccopPerformance) {
        this.performance = ccopPerformance;
    }

    public String getResumeDoc() {
        return resumeDoc;
    }

    public void setResumeDoc(String resumeDoc) {
        this.resumeDoc = resumeDoc;
    }
}
