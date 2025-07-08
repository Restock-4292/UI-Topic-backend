package com.restock.platform.subscription.interfaces.rest.resources;

import java.util.List;
import java.util.UUID;

public record SubscriptionPlanResource(UUID id, String name, int duration, double price, String currency, boolean popular, List<String> features, int roleId) {}