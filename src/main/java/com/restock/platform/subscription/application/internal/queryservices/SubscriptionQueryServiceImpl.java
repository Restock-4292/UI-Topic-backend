package com.restock.platform.subscription.application.internal.queryservices;

import com.restock.platform.subscription.domain.model.entities.Subscription;
import com.restock.platform.subscription.domain.model.queries.GetSubscriptionsByAccountIdQuery;
import com.restock.platform.subscription.domain.services.SubscriptionQueryService;
import com.restock.platform.subscription.infrastructure.persistence.jpa.repositories.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SubscriptionQueryServiceImpl implements SubscriptionQueryService {

    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionQueryServiceImpl(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    public List<Subscription> handle(GetSubscriptionsByAccountIdQuery query) {
        return subscriptionRepository.findByAccountId(query.accountId());
    }

    @Override
    public Optional<Subscription> findById(UUID id) {
        return subscriptionRepository.findById(id);
    }

    @Override
    public List<Subscription> findByAccountId(UUID accountId) {
        return subscriptionRepository.findByAccountId(accountId);
    }
}