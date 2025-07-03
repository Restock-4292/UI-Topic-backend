package com.restock.platform.resource.interfaces.rest.resources;

public record SupplyResource(
        String name,
        String description,
        boolean perishable,
        String unitName,
        String unitAbbreviation,
        String category
) {}
