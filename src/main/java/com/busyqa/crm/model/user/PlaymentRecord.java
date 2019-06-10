package com.busyqa.crm.model.user;

import java.util.ArrayList;
import java.util.List;

public class PlaymentRecord {
    private String paymentPlanType;
    private List<Payment> paymentHistory = new ArrayList<>();

    public String getPaymentPlanType() {
        return paymentPlanType;
    }

    public void setPaymentPlanType(String paymentPlanType) {
        this.paymentPlanType = paymentPlanType;
    }

    public List<Payment> getPaymentHistory() {
        return paymentHistory;
    }

    public void setPaymentHistory(List<Payment> paymentHistory) {
        this.paymentHistory = paymentHistory;
    }
}
