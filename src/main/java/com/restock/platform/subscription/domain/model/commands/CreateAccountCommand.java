package com.restock.platform.subscription.domain.model.commands;

import com.restock.platform.subscription.domain.model.valueobjects.SubscriptionPlanType;

import java.util.List;
import java.util.UUID;

public record CreateAccountCommand(
        UUID userId,
        String email,
        UUID subscriptionPlanId
) {
    public CreateAccountCommand {
        if (userId == null || email == null || email.isBlank())
            throw new IllegalArgumentException("User ID and email are required");

        if (subscriptionPlanId == null)
            throw new IllegalArgumentException("SubscriptionPlanId is required");
    }
}
