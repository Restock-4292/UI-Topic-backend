package com.restock.platform.subscription.domain.services;

import com.restock.platform.subscription.domain.model.aggregates.Account;
import com.restock.platform.subscription.domain.model.queries.GetAccountByIdQuery;
import com.restock.platform.subscription.domain.model.queries.GetAccountsBySubscriptionTypeQuery;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountQueryService {
    Optional<Account> handle(GetAccountByIdQuery query);
    List<Account> handle(GetAccountsBySubscriptionTypeQuery query);

    Optional<Account> findById(UUID id);
}
