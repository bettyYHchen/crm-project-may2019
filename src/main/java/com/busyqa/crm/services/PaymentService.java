package com.busyqa.crm.services;

import com.busyqa.crm.exception.ResourceNotFoundException;
import com.busyqa.crm.model.academic.Course;
import com.busyqa.crm.model.academic.TrainingClass;
import com.busyqa.crm.model.user.User;
import com.busyqa.crm.model.user.payment.Payment;
import com.busyqa.crm.model.user.payment.PaymentStatus;
import com.busyqa.crm.repo.CourseRepository;
import com.busyqa.crm.repo.PaymentRepository;
import com.busyqa.crm.repo.TrainingClassRepository;
import com.busyqa.crm.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private TrainingClassRepository trainingClassRepository;


    public List<Payment> generatePayments(String className, String paymentPlan, double feePaid, User user) {
        TrainingClass trainingClass = trainingClassRepository.findByName(className).orElseThrow(() ->
                new RuntimeException("Error: TrainingClass not found!")
        );
        double regularFee = trainingClass.getCourseFee() - feePaid;
        double taxFee = (regularFee*(trainingClass.getTaxRate()))/100.0;
        double lateFeeRate = trainingClass.getLateFeeRate();
        List<Payment> payments = Common.generatePayments(paymentPlan, regularFee, taxFee,0,
                trainingClass.getPaymentDurationWeekly(), trainingClass.getPaymentDurationBiWeekly());
        List<String> dates = Common.generatePaymentDate(trainingClass.getStart(), paymentPlan,
                trainingClass.getPaymentDurationWeekly(),trainingClass.getPaymentDurationBiWeekly());
        if (payments.size() != dates.size()) throw new RuntimeException("Error: check Common!");
        for (int i = 0; i<payments.size(); i++) {
            if ((Common.checkDateCondition(dates.get(i))) &&
                    (payments.get(i).getStatus().equals(PaymentStatus.UNPAID.toString()))) {
                double amountBeforeLateFee = payments.get(i).getAmount();
                payments.get(i).setLateFee((amountBeforeLateFee*lateFeeRate)/100.0);
                payments.get(i).setAmount(amountBeforeLateFee+payments.get(i).getLateFee());
            }
            payments.get(i).setDate(dates.get(i));
            payments.get(i).setUser(user);
            paymentRepository.save(payments.get(i));
        }
        return payments;

    }

    public void deletePayments(Long userId) {
        Pageable pageable = PageRequest.of(0, 30);
        Page<Payment> paymentsPage = paymentRepository.findByUserId(userId,pageable);
        List<Payment> payments = paymentsPage.getContent();
        for (Payment p: payments) {
            paymentRepository.delete(p);
        }
    }

    public double getUpdatePaidAmount(Long userId) {
        Pageable pageable = PageRequest.of(0, 30);
        Page<Payment> paymentsPage = paymentRepository.findByUserId(userId,pageable);
        List<Payment> payments = paymentsPage.getContent();
        return Common.getPaidFee(payments);

    }

    public List<Payment> updatePayments(Long userId, String className) {
        TrainingClass trainingClass = trainingClassRepository.findByName(className).orElseThrow(() ->
                new RuntimeException("Error: TrainingClass not found!")
        );
        double lateFeeRate = trainingClass.getLateFeeRate();
        Pageable pageable = PageRequest.of(0, 30, Sort.by("date"));
        Page<Payment> paymentsPage = paymentRepository.findByUserId(userId,pageable);
        List<Payment> payments = paymentsPage.getContent();
        for (Payment p: payments) {
            if ((Common.checkDateCondition(p.getDate())) &&
                    (p.getStatus().equals(PaymentStatus.UNPAID.toString()))) {
                double amountBeforeLateFee = p.getAmount();
                p.setLateFee((amountBeforeLateFee*lateFeeRate)/100.0);
                p.setAmount(amountBeforeLateFee+p.getLateFee());
            }
            paymentRepository.save(p);
        }
        return payments;

    }

}
