package com.busyqa.crm.message.request;

public class CourseRequest {

    private String name;
    private double fee;
    private double durationWeek;

    private double paymentDurationWeek;


    private double paymentDurationBiWeek;

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
}
