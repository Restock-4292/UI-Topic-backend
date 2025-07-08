package com.restock.platform.subscription.domain.model.entities;

import com.restock.platform.subscription.domain.model.valueobjects.Money;
import com.restock.platform.subscription.domain.model.valueobjects.SubscriptionPlanType;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@Entity
public class SubscriptionPlan {
    @Id
    private UUID id;

    @Enumerated(EnumType.STRING)
    private SubscriptionPlanType planType;

    private int durationInMonths;

    @Embedded
    private Money price;

    private boolean active;

    private boolean popular;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> features;

    @Column(name = "role_id", nullable = false)
    private int roleId;

    protected SubscriptionPlan() {}

    public SubscriptionPlan(String planType, int durationInMonths, Money price,
                            boolean active, boolean popular, List<String> features, int roleId) {
        this.id = UUID.randomUUID();
        this.planType = SubscriptionPlanType.valueOf(planType.toUpperCase());
        this.durationInMonths = durationInMonths;
        this.price = price;
        this.active = active;
        this.popular = popular;
        this.features = features;
        this.roleId = roleId;
    }


}
