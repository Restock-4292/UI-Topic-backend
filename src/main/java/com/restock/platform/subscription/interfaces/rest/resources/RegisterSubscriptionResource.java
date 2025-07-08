package com.restock.platform.subscription.interfaces.rest.resources;

import com.restock.platform.subscription.domain.model.valueobjects.SubscriptionPlanType;

import java.util.List;
import java.util.UUID;

public record RegisterSubscriptionResource(
        UUID accountId,
        UUID subscriptionPlanId,
        SubscriptionPlanType plan,
        int durationInMonths,
        double price,
        String currency,
        boolean popular,
        List<String> features
) {}