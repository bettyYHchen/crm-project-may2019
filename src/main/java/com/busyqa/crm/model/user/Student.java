package com.busyqa.crm.model.user;

import com.busyqa.crm.model.academic.TrainingClass;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;


@Entity
@DiscriminatorValue("3")
public class Student extends User{

    @NotBlank
    @Size(min = 3, max = 50)
    private String phone;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "training_class_id", referencedColumnName = "id")
    @JsonBackReference
    private TrainingClass trainingClass;

    @NotBlank
    @Size(min = 3, max = 80)
    private String paymentPlan;


    private int remainingBalance;

    public Student() {
    }

    public Student(String name, String username, String email, String password,  String status, String statusAsOfDay, @NotBlank @Size(min = 3, max = 50) String phone, TrainingClass trainingClass, @NotBlank @Size(min = 3, max = 80) String paymentPlan, int remainingBalance) {
        super(name, username, email, password, status, statusAsOfDay);
        this.phone = phone;
        this.trainingClass = trainingClass;
        this.paymentPlan = paymentPlan;
        this.remainingBalance = remainingBalance;
    }



    public Student(String name, String username, String email, String password, Set<Position> positions, String status, String statusAsOfDay, @NotBlank @Size(min = 3, max = 50) String phone, TrainingClass trainingClass, @NotBlank @Size(min = 3, max = 80) String paymentPlan, int remainingBalance) {
        super(name, username, email, password, positions, status, statusAsOfDay);
        this.phone = phone;
        this.trainingClass = trainingClass;
        this.paymentPlan = paymentPlan;
        this.remainingBalance = remainingBalance;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public TrainingClass getTrainingClass() {
        return trainingClass;
    }

    public void setTrainingClass(TrainingClass trainingClass) {
        this.trainingClass = trainingClass;
    }

    public String getPaymentPlan() {
        return paymentPlan;
    }

    public void setPaymentPlan(String paymentPlan) {
        this.paymentPlan = paymentPlan;
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

    @Override
    public String toString() {
        return "Student{" +
                "phone='" + phone + '\'' +
                ", trainingClass=" + trainingClass +
                ", paymentPlan='" + paymentPlan + '\'' +
                ", remainingBalance=" + remainingBalance  + '\'' +
                ", isFinishedClass=" + this.finishedClass() +
                '}';
    }
}
