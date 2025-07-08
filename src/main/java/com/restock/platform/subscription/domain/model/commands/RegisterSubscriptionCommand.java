package com.restock.platform.subscription.domain.model.commands;

import com.restock.platform.subscription.domain.model.valueobjects.SubscriptionPlanType;

import java.util.List;
import java.util.UUID;

public record RegisterSubscriptionCommand(
        UUID accountId,
        UUID subscriptionPlanId,
        SubscriptionPlanType plan,
        int durationInMonths,
        double price,
        String currency,
        boolean popular,
        List<String> features
) {
    public RegisterSubscriptionCommand {
        if (accountId == null) {
            throw new IllegalArgumentException("Account ID cannot be null");
        }
        if (subscriptionPlanId == null) {
            throw new IllegalArgumentException("Subscription Plan ID cannot be null");
        }
        if (plan == null) {
            throw new IllegalArgumentException("Plan cannot be null");
        }
        if (durationInMonths <= 0) {
            throw new IllegalArgumentException("Duration must be greater than zero");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        if (currency == null || currency.isBlank()) {
            throw new IllegalArgumentException("Currency cannot be null or blank");
        }
        if (features == null || features.isEmpty()) {
            throw new IllegalArgumentException("Features list cannot be null or empty");
        }
    }
}
