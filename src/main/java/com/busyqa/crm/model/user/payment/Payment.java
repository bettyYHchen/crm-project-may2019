package com.busyqa.crm.model.user.payment;

import com.busyqa.crm.model.user.User;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String date;

    private double regularFee;

    private double taxFee;

    private double lateFee;

    private double amount;

    private String status;

    private String paidDate;

    private String transactionNumber;



    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    public Payment() {
    }

    public Payment(String date, double regularFee, double taxFee, double lateFee, double amount, String status) {
        this.date = date;
        this.regularFee = regularFee;
        this.taxFee = taxFee;
        this.lateFee = lateFee;
        this.amount = amount;
        this.status = status;
    }

    public Payment(String date, double regularFee, double taxFee, double lateFee, double amount, String status, String paidDate, String transactionNumber) {
        this.date = date;
        this.regularFee = regularFee;
        this.taxFee = taxFee;
        this.lateFee = lateFee;
        this.amount = amount;
        this.status = status;
        this.paidDate = paidDate;
        this.transactionNumber = transactionNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getLateFee() {
        return lateFee;
    }

    public void setLateFee(double lateFee) {
        this.lateFee = lateFee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getRegularFee() {
        return regularFee;
    }

    public void setRegularFee(double regularFee) {
        this.regularFee = regularFee;
    }

    public double getTaxFee() {
        return taxFee;
    }

    public void setTaxFee(double taxFee) {
        this.taxFee = taxFee;
    }

    public String getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(String paidDate) {
        this.paidDate = paidDate;
    }

    public String getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    @JsonIgnore
    public double getPaidAmount() {
        if( status.equals(PaymentStatus.PAID.toString())) {
            return (regularFee + taxFee);
        }else if (status.equals(PaymentStatus.PAID_WITH_LATEFEE.toString())) {
            return amount;
        }else {return 0;}

    }
}
