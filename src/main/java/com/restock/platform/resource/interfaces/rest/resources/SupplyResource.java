package com.restock.platform.resource.interfaces.rest.resources;

public record SupplyResource(
        Long id,
        Long referenceSupplyId,
        String description,
        int minStock,
        int maxStock,
        double price,
        String currencyCode,
        Long userId
) {}
