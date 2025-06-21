package com.restock.platform.resource.interfaces.rest.resources;

public record UpdateSupplyResource(
        String description,
        int minStock,
        int maxStock,
        double price
) {
    public UpdateSupplyResource {
        if (description == null || description.isBlank())
            throw new IllegalArgumentException("Description is required");
        if (minStock < 0)
            throw new IllegalArgumentException("Min stock must be non-negative");
        if (maxStock < minStock)
            throw new IllegalArgumentException("Max stock must be greater than or equal to min stock");
        if (price < 0)
            throw new IllegalArgumentException("Price must be non-negative");
    }
}
