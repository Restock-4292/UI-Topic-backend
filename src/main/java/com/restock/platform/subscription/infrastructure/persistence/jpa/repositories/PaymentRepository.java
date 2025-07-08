package com.restock.platform.subscription.infrastructure.persistence.jpa.repositories;

import com.restock.platform.subscription.domain.model.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, UUID> {
    List<Payment> findByAccountId(UUID accountId);
}