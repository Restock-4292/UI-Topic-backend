package com.restock.platform.resource.interfaces.rest.resources;

public record ReferenceSupplyResource(
        Long id,
        String name,
        String description,
        boolean perishable,
        String unitName,
        String unitAbbreviation,
        String category
) {}
