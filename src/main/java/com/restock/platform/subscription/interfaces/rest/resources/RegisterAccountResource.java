package com.restock.platform.subscription.interfaces.rest.resources;

public record RegisterAccountResource(
        String email,
        String subscriptionPlanId
) {}
