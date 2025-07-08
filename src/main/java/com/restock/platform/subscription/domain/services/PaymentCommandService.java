package com.restock.platform.subscription.domain.services;

import com.restock.platform.subscription.domain.model.commands.RegisterPaymentCommand;
import com.restock.platform.subscription.domain.model.entities.Payment;

import java.util.Optional;

public interface PaymentCommandService {
    Optional<Payment> handle(RegisterPaymentCommand command);
}
