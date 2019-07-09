package com.busyqa.crm.message.request;

public class SettingRequest {
    private double taxPercentage;

    private double lateFeeRate;

    private double creditExtraRate;

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

    public double getCreditExtraRate() {
        return creditExtraRate;
    }

    public void setCreditExtraRate(double creditExtraRate) {
        this.creditExtraRate = creditExtraRate;
    }
}
