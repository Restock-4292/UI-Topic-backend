package com.restock.platform.subscription.infrastructure.persistence.jpa.repositories;

import com.restock.platform.subscription.domain.model.entities.SubscriptionPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SubscriptionPlanRepository extends JpaRepository<SubscriptionPlan, UUID> {

    // Buscar por tipo de plan (ej: MONTHLY, YEARLY, etc.)
    List<SubscriptionPlan> findByPlanType(String planType);

    // Buscar todos los planes activos
    List<SubscriptionPlan> findByStatusTrue();

    // Buscar por tipo de plan y rolId
    List<SubscriptionPlan> findByPlanTypeAndRoleId(String planType, int roleId);

    // Buscar todos los planes por rolId
    List<SubscriptionPlan> findByRoleId(int roleId);
}