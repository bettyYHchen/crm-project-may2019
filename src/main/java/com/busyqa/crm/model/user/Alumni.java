package com.busyqa.crm.model.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "alumnus")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Alumni {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    private String name;
    private String email;
    private String phone;
    private String placementStatus;
    private String companyName;
    private String employmentType;
    private String designation;
    private String comment;

    public Alumni() {
    }

    public Alumni(String name, String email, String phone, String placementStatus, String companyName, String employmentType, String designation, String comment) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.placementStatus = placementStatus;
        this.companyName = companyName;
        this.employmentType = employmentType;
        this.designation = designation;
        this.comment = comment;
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

    public String getPlacementStatus() {
        return placementStatus;
    }

    public void setPlacementStatus(String placementStatus) {
        this.placementStatus = placementStatus;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
