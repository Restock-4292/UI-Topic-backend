package com.restock.platform.subscription.domain.model.commands;

import java.util.UUID;

public record RegisterPaymentCommand(
        UUID accountId,
        String paymentMethod,
        double amount,
        String currency
) {
    public RegisterPaymentCommand {
        if (accountId == null) {
            throw new IllegalArgumentException("Account ID cannot be null");
        }
        if (paymentMethod == null || paymentMethod.isBlank()) {
            throw new IllegalArgumentException("Payment method cannot be null or blank");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        if (currency == null || currency.isBlank()) {
            throw new IllegalArgumentException("Currency cannot be null or blank");
        }
    }
}
