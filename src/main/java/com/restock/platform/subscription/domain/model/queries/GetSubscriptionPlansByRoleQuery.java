package com.restock.platform.subscription.domain.model.queries;

public record GetSubscriptionPlansByRoleQuery(int roleId) {
    public GetSubscriptionPlansByRoleQuery {
        if (roleId <= 0) {
            throw new IllegalArgumentException("Role ID must be greater than zero");
        }
    }
}
