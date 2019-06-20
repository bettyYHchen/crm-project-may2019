package com.busyqa.crm.message.request;

public class PaymentRequest {
    private String date;
    private double regularFee;
    private double taxFee;
    private double lateFee;
    private double amount;
    private String status;

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
}
