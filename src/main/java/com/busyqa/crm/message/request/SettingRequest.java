package com.busyqa.crm.message.request;

public class SettingRequest {
    private double taxPercentage;

    private double lateFeeRate;

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
}
