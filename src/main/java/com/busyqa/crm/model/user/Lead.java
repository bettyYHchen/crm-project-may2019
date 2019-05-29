package com.busyqa.crm.model.user;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@DiscriminatorValue("2")
public class Lead extends User{

    private String phone;

    private Boolean paidDeposite;

    @NotBlank
    @Size(min = 3, max = 80)
    private String aTrainingClassName; // "FULLSTACK_JAVA_DEVELOPER WINTER 2019"

    public Lead() {
    }

    public Lead(String name, String username, String email, String password, Set<Position> positions, String status, String statusAsOfDay, String phone, Boolean paidDeposite, @NotBlank @Size(min = 3, max = 80) String aTrainingClassName) {
        super(name, username, email, password, positions, status, statusAsOfDay);
        this.phone = phone;
        this.paidDeposite = paidDeposite;
        this.aTrainingClassName = aTrainingClassName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getPaidDeposite() {
        return paidDeposite;
    }

    public void setPaidDeposite(Boolean paidDeposite) {
        this.paidDeposite = paidDeposite;
    }

    public String getaTrainingClassName() {
        return aTrainingClassName;
    }

    public void setaTrainingClassName(String aTrainingClassName) {
        this.aTrainingClassName = aTrainingClassName;
    }

    @Override
    public String toString() {
        return "Lead{" +
                "phone='" + phone + '\'' +
                ", paidDeposite=" + paidDeposite +
                ", aTrainingClassName='" + aTrainingClassName + '\'' +
                '}';
    }
}
