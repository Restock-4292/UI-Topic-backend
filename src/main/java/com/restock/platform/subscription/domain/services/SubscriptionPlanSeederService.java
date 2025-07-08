package com.restock.platform.subscription.domain.services;


import com.restock.platform.subscription.domain.model.entities.SubscriptionPlan;

import java.util.List;

public interface SubscriptionPlanSeederService {
    void seed(List<SubscriptionPlan> plans);
}
