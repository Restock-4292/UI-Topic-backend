package com.restock.platform.subscription.domain.model.entities;

import com.restock.platform.subscription.domain.model.valueobjects.Money;
import com.restock.platform.subscription.domain.model.valueobjects.SubscriptionPlanType;
import com.restock.platform.subscription.domain.model.valueobjects.SubscriptionStatus;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Entity
public class Subscription {

    // Getters
    @Id
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscription_plan_id", nullable = false)
    private SubscriptionPlan plan;

    @Enumerated(EnumType.STRING)
    private SubscriptionStatus status;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    protected Subscription() {
    }

    public Subscription(SubscriptionPlan plan) {
        this.id = UUID.randomUUID();
        this.plan = plan;
        this.status = SubscriptionStatus.Active;
        this.startDate = LocalDateTime.now();
        this.endDate = startDate.plusMonths(plan.getDurationInMonths());
    }

    public boolean isActive() {
        return status == SubscriptionStatus.Active && LocalDateTime.now().isBefore(endDate);
    }

    public void cancel() {
        this.status = SubscriptionStatus.Inactive;
        this.endDate = LocalDateTime.now();
    }

}
