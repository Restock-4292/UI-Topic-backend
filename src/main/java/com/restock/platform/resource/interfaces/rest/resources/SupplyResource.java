package com.restock.platform.resource.interfaces.rest.resources;

public record SupplyResource(
        Long id,
        String name,
        String description,
        boolean perishable,
        int minStock,
        int maxStock,
        double price,
        Long userId,
        String unitMeasurementName,
        String unitMeasurementAbbreviation,
        Long categoryId
) {}
