package com.busyqa.crm.model.academic;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "courses")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;


    @Column(name = "name",unique=true)
    private String name;

    private double fee;

    private double taxPercentage;

    private double lateFeeRate;

    private double durationWeek;

    private double paymentDurationWeek;


    private double paymentDurationBiWeek;

    private double depositAmount;

    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
    @Column(nullable = true)
    @JsonManagedReference
    private List<TrainingClass> trainingClasses;

    public void addTrainingClass(TrainingClass trainingClass) {
        this.trainingClasses.add(trainingClass);
    }

    public void removeTrainingClass(TrainingClass trainingClass) {
        Boolean exist = false;
        for (TrainingClass t: trainingClasses) {
            if (trainingClass.getName().equals(t.getName())) {
                exist = true;
            }
        }
        if (exist) {
            this.trainingClasses.remove(trainingClass);
        }

    }

    public Course() {
    }

    public Course(String name, double fee) {
        this.name = name;
        this.fee = fee;
    }

    public Course( String name, double fee, List<TrainingClass> trainingClasses) {
        this.name = name;
        this.fee = fee;
        this.trainingClasses = trainingClasses;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public List<TrainingClass> getTrainingClasses() {
        return trainingClasses;
    }

    public void setTrainingClasses(List<TrainingClass> trainingClasses) {
        this.trainingClasses = trainingClasses;
    }

    public double getDurationWeek() {
        return durationWeek;
    }

    public void setDurationWeek(double durationWeek) {
        this.durationWeek = durationWeek;
    }

    public double getPaymentDurationWeek() {
        return paymentDurationWeek;
    }

    public void setPaymentDurationWeek(double paymentDurationWeek) {
        this.paymentDurationWeek = paymentDurationWeek;
    }


    public double getPaymentDurationBiWeek() {
        return paymentDurationBiWeek;
    }

    public void setPaymentDurationBiWeek(double paymentDurationBiWeek) {
        this.paymentDurationBiWeek = paymentDurationBiWeek;
    }

    public double getTaxPercentage() {
        return taxPercentage;
    }

    public void setTaxPercentage(double taxPercentage) {
        this.taxPercentage = taxPercentage;
    }

    public double getLateFeeRate() {
        return lateFeeRate;
    }

    public void setLateFeeRate(double lateFeeRate) {
        this.lateFeeRate = lateFeeRate;
    }

    public double getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(double depositAmount) {
        this.depositAmount = depositAmount;
    }
}
