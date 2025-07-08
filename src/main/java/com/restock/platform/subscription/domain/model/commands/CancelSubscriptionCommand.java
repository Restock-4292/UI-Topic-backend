package com.restock.platform.subscription.domain.model.commands;

import java.util.UUID;

public record CancelSubscriptionCommand(UUID accountId, UUID subscriptionId) {
    public CancelSubscriptionCommand {
        if (accountId == null) throw new IllegalArgumentException("Account ID cannot be null");
        if (subscriptionId == null) throw new IllegalArgumentException("Subscription ID cannot be null");
    }
}
