package com.restock.platform.subscription.domain.model.queries;

import java.util.UUID;

public record GetSubscriptionsByAccountIdQuery(UUID accountId) {
    public GetSubscriptionsByAccountIdQuery {
        if (accountId == null) {
            throw new IllegalArgumentException("Account ID cannot be null");
        }
    }
}
