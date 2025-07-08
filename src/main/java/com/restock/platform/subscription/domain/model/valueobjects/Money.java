package com.restock.platform.subscription.domain.model.valueobjects;


public record Money(double amount, String currency) {

    public Money{
        if(amount < 0) throw new IllegalArgumentException("Amount cannot be negative");
        if(currency == null || currency.isBlank()) throw new IllegalArgumentException("Currency cannot be null or empty");
    }
}
