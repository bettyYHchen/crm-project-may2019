package com.busyqa.crm.repo;

import com.busyqa.crm.model.user.payment.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Page<Payment> findByUserId(Long userId, Pageable pageable);
    Optional<Payment> findByIdAndUserId(Long id, Long userId);

}
