package com.restock.platform.subscription.interfaces.rest.resources;

public record RegisterPaymentResource(String paymentMethod, double amount, String currency) {}