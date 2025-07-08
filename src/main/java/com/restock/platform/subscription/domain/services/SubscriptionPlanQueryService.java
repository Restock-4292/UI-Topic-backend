package com.restock.platform.subscription.domain.services;

import com.restock.platform.subscription.domain.model.entities.SubscriptionPlan;
import com.restock.platform.subscription.domain.model.queries.GetSubscriptionPlansByRoleQuery;

import java.util.List;

public interface SubscriptionPlanQueryService {
    List<SubscriptionPlan> handle(GetSubscriptionPlansByRoleQuery query);
}