package com.busyqa.crm.utils;

import com.busyqa.crm.model.user.Lead;
import com.busyqa.crm.model.user.User;
import com.busyqa.crm.model.user.payment.Payment;
import com.busyqa.crm.model.user.payment.PaymentPlan;
import com.busyqa.crm.model.user.payment.PaymentStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Common {
    public List<User> mergeTwoList(List<User> l1, List<User> l2){

        for (User x : l2){
            if (!l1.contains(x))
                l1.add(x);
        }
        return l1;
    }

    // generate payments based on payment plan
    public static List<Payment> generatePayments(String paymentPlan, double regularFee, double taxFee, double lateFee,
                                          double weekFrequency, double biWeekFrequency) {
        List<Payment> payments = new ArrayList<>();
        if (paymentPlan.equals(PaymentPlan.One_Time_Credit_Card.toString()) ||
                paymentPlan.equals(PaymentPlan.One_Time_Debit_Card_Or_Cash.toString()) ||
                paymentPlan.equals(PaymentPlan.One_Time_Email_Money.toString())) {
            payments.add(new Payment("", regularFee, taxFee, lateFee,
                    regularFee+taxFee+lateFee,PaymentStatus.UNPAID.toString()));
        }else if (paymentPlan.equals(PaymentPlan.Automated_Weekly.toString())) {
            for (int i = 0; i < weekFrequency; i++) {
                payments.add(new Payment("", regularFee/weekFrequency, taxFee/weekFrequency, lateFee,
                        ((regularFee+taxFee)/weekFrequency)+lateFee, PaymentStatus.UNPAID.toString()));
            }
        }else {
            for (int i = 0; i < biWeekFrequency; i++) {
                payments.add(new Payment("", regularFee/biWeekFrequency, taxFee/biWeekFrequency, lateFee,
                        ((regularFee+taxFee)/biWeekFrequency)+lateFee, PaymentStatus.UNPAID.toString()));
            }
        }
        return payments;
    }

    // generate payment dates
    // given start date, payment plan
    public static List<String> generatePaymentDate(String dateString, String paymentPlan,
                    double weekFrequency, double biWeekFrequency) {
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate date = LocalDate.parse(dateString, myFormatObj);
        List<String> dates2Generate = new ArrayList<>();
        if (paymentPlan.equals(PaymentPlan.One_Time_Credit_Card.toString()) ||
                paymentPlan.equals(PaymentPlan.One_Time_Debit_Card_Or_Cash.toString()) ||
                paymentPlan.equals(PaymentPlan.One_Time_Email_Money.toString())) {
            // if one time payment selected, the deadline to pay the fee would be one week
            // after the class started.
            dates2Generate.add(date.plus(1, ChronoUnit.WEEKS).format(myFormatObj));
        }else if (paymentPlan.equals(PaymentPlan.Automated_Weekly.toString())) {
            for (int i = 0; i < weekFrequency; i++) {
                dates2Generate.add(date.plus(i*7+3, ChronoUnit.DAYS).format(myFormatObj));
            }
        }
        else {
            for (int i = 0; i < biWeekFrequency; i++) {
                dates2Generate.add(date.plus(i*14+3, ChronoUnit.DAYS).format(myFormatObj));
            }
        }
        return dates2Generate;

    }

    // calculate late fee given late fee rule

    // calculate paid amount of a lead
    public static double calculatePaidAmount(boolean paidDeposit, double discount) {
        if (paidDeposit) {
            return 400+discount;
        }else { return  discount;}
    }

    // given a date string, give true if this date is more than one week of the present date
    public static boolean checkDateCondition(String dateString) {
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate event = LocalDate.parse(dateString, myFormatObj);
        LocalDate today = LocalDate.now();
        LocalDate weekBeforeToday = today.minusWeeks(1);
        System.out.println(weekBeforeToday.compareTo(event));
        if (weekBeforeToday.compareTo(event) > 0) {
            return true; // you need to pay late fee because event is more than 7 days ago
        } else {
            return false;
        }
    }

    public static double getPaidFee(List<Payment> payments) {
        int amountpaid = 0;
        for (Payment p: payments) {
            amountpaid += p.getPaidAmount();
        }
        return amountpaid;
    }



}
