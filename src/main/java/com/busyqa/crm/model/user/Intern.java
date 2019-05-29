package com.busyqa.crm.model.user;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@DiscriminatorValue("4")
public class Intern extends User{

    @NotBlank
    @Size(min = 3, max = 50)
    private String phone;

    @NotBlank
    private String coopStatus;

    @NotBlank
    @Size(min = 3, max = 80)
    private String aTrainingClassName; // "FULLSTACK_JAVA_DEVELOPER WINTER 2019"

    public Intern() {
    }

    public Intern(String name, String username, String email, String password, Set<Position> positions, String status, String statusAsOfDay, @NotBlank @Size(min = 3, max = 50) String phone, @NotBlank String coopStatus, @NotBlank @Size(min = 3, max = 80) String aTrainingClassName) {
        super(name, username, email, password, positions, status, statusAsOfDay);
        this.phone = phone;
        this.coopStatus = coopStatus;
        this.aTrainingClassName = aTrainingClassName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCoopStatus() {
        return coopStatus;
    }

    public void setCoopStatus(String coopStatus) {
        this.coopStatus = coopStatus;
    }

    public String getaTrainingClassName() {
        return aTrainingClassName;
    }

    public void setaTrainingClassName(String aTrainingClassName) {
        this.aTrainingClassName = aTrainingClassName;
    }
}
