package com.restock.platform.subscription.domain.services;

import com.restock.platform.subscription.domain.model.aggregates.Account;
import com.restock.platform.subscription.domain.model.commands.CreateAccountCommand;
import com.restock.platform.subscription.domain.model.commands.RegisterPaymentCommand;
import com.restock.platform.subscription.domain.model.commands.RegisterSubscriptionCommand;

import java.util.Optional;
import java.util.UUID;

public interface AccountCommandService {
    Optional<Account> handle(CreateAccountCommand command);
    Optional<Account> handle(RegisterSubscriptionCommand command);
    Optional<Account> handle(RegisterPaymentCommand command);
}
