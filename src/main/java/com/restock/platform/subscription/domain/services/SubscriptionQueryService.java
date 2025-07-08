package com.restock.platform.subscription.domain.services;

import com.restock.platform.subscription.domain.model.entities.Subscription;
import com.restock.platform.subscription.domain.model.queries.GetSubscriptionsByAccountIdQuery;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SubscriptionQueryService {
    List<Subscription> handle(GetSubscriptionsByAccountIdQuery query);

    Optional<Subscription> findById(UUID id);

    List<Subscription> findByAccountId(UUID accountId);
}
