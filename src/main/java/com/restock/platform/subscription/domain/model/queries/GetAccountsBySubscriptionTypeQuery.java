package com.restock.platform.subscription.domain.model.queries;

import com.restock.platform.subscription.domain.model.valueobjects.SubscriptionPlanType;

public record GetAccountsBySubscriptionTypeQuery(SubscriptionPlanType planType) {
    public GetAccountsBySubscriptionTypeQuery {
        if (planType == null) {
            throw new IllegalArgumentException("Plan type cannot be null");
        }
    }
}