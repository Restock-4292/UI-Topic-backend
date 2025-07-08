package com.restock.platform.subscription.interfaces.rest.resources;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record SubscriptionResource(
        UUID subscriptionId,
        UUID accountId,
        UUID subscriptionPlanId,
        String planType, // String para el enum SubscriptionPlanType en la salida
        int durationInMonths,
        double price,
        String currency,
        boolean popular,
        List<String> features,
        LocalDate startDate,
        LocalDate endDate,
        String status
) {}
