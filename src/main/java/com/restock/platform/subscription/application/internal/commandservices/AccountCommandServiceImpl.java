package com.restock.platform.subscription.application.internal.commandservices;

import com.restock.platform.subscription.domain.model.aggregates.Account;
import com.restock.platform.subscription.domain.model.commands.CreateAccountCommand;
import com.restock.platform.subscription.domain.model.commands.RegisterPaymentCommand;
import com.restock.platform.subscription.domain.model.commands.RegisterSubscriptionCommand;
import com.restock.platform.subscription.domain.model.entities.Payment;
import com.restock.platform.subscription.domain.model.entities.Subscription;
import com.restock.platform.subscription.domain.services.AccountCommandService;
import com.restock.platform.subscription.infrastructure.persistence.jpa.repositories.AccountRepository;
import com.restock.platform.subscription.infrastructure.persistence.jpa.repositories.SubscriptionPlanRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountCommandServiceImpl implements AccountCommandService {

    private final AccountRepository accountRepository;
    private final SubscriptionPlanRepository subscriptionPlanRepository;

    public AccountCommandServiceImpl(AccountRepository accountRepository, SubscriptionPlanRepository subscriptionPlanRepository) {
        this.accountRepository = accountRepository;
        this.subscriptionPlanRepository = subscriptionPlanRepository;
    }

    @Override
    public Optional<Account> handle(CreateAccountCommand command) {
        var plan = subscriptionPlanRepository.findById(command.subscriptionPlanId())
                .orElseThrow(() -> new IllegalArgumentException("Subscription plan not found"));

        var account = new Account(command, plan);
        var savedAccount = accountRepository.save(account);
        return Optional.of(savedAccount);
    }


    @Override
    public Optional<Account> handle(RegisterSubscriptionCommand command) {
        var account = accountRepository.findById(command.accountId())
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
        var plan = subscriptionPlanRepository.findById(command.subscriptionPlanId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid subscription plan ID"));

        var subscription = new Subscription(plan);
        account.addSubscription(subscription);
        return Optional.of(accountRepository.save(account));
    }

    @Override
    public Optional<Account> handle(RegisterPaymentCommand command) {
        var account = accountRepository.findById(command.accountId())
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        var payment = new Payment(command);
        account.registerPayment(payment);
        return Optional.of(accountRepository.save(account));
    }
}
