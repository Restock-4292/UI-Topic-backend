package com.restock.platform.subscription.application.internal.queryservices;


import com.restock.platform.subscription.domain.model.entities.SubscriptionPlan;
import com.restock.platform.subscription.domain.model.queries.GetSubscriptionPlansByRoleQuery;
import com.restock.platform.subscription.domain.services.SubscriptionPlanQueryService;
import com.restock.platform.subscription.infrastructure.persistence.jpa.repositories.SubscriptionPlanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionPlanQueryServiceImpl implements SubscriptionPlanQueryService {

    private final SubscriptionPlanRepository repository;

    public SubscriptionPlanQueryServiceImpl(SubscriptionPlanRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<SubscriptionPlan> handle(GetSubscriptionPlansByRoleQuery query) {
        return repository.findByRoleId(query.roleId());
    }
}