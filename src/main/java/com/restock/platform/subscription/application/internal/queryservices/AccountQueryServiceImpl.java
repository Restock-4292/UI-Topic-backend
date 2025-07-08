package com.restock.platform.subscription.application.internal.queryservices;


import com.restock.platform.subscription.domain.model.aggregates.Account;
import com.restock.platform.subscription.domain.model.queries.GetAccountByIdQuery;
import com.restock.platform.subscription.domain.model.queries.GetAccountsBySubscriptionTypeQuery;
import com.restock.platform.subscription.domain.services.AccountQueryService;
import com.restock.platform.subscription.infrastructure.persistence.jpa.repositories.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountQueryServiceImpl implements AccountQueryService {

    private final AccountRepository accountRepository;

    public AccountQueryServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Optional<Account> handle(GetAccountByIdQuery query) {
        return accountRepository.findById(query.accountId());
    }

    @Override
    public List<Account> handle(GetAccountsBySubscriptionTypeQuery query) {
        return accountRepository.findAllBySubscriptionPlanType(query.planType());
    }

    @Override
    public Optional<Account> findById(UUID id) {
        return accountRepository.findById(id);
    }

}