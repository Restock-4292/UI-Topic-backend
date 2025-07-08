package com.restock.platform.subscription.domain.services;

import com.restock.platform.subscription.domain.model.commands.CancelSubscriptionCommand;
import com.restock.platform.subscription.domain.model.commands.RegisterSubscriptionCommand;
import com.restock.platform.subscription.domain.model.commands.SeedSubscriptionPlansCommand;
import com.restock.platform.subscription.domain.model.entities.Subscription;

import java.util.Optional;

public interface SubscriptionCommandService {
    Optional<Subscription> handle(RegisterSubscriptionCommand command);
    Optional<Subscription> handle(CancelSubscriptionCommand command);
}
