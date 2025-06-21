package com.test.oop2.repository;

import com.test.oop2.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PaymentRepo extends JpaRepository<Payment, UUID> {
    List<Payment> findByUserId(UUID userId);
    List<Payment> findByUserIdAndBranch(UUID userId, String branch);
}
