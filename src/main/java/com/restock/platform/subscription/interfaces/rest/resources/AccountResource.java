package com.restock.platform.subscription.interfaces.rest.resources;

import java.util.UUID;

public record AccountResource(
        UUID accountId,
        String email,
        UUID subscriptionPlanId
) {}
