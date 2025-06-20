package com.restock.platform.resource.interfaces.rest.resources;

public record CreateSupplyResource(
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
) {
    public CreateSupplyResource {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Name is required");
        if (description == null || description.isBlank())
            throw new IllegalArgumentException("Description is required");
        if (minStock < 0)
            throw new IllegalArgumentException("Min stock must be non-negative");
        if (maxStock < minStock)
            throw new IllegalArgumentException("Max stock must be greater than or equal to min stock");
        if (price < 0)
            throw new IllegalArgumentException("Price must be non-negative");
        if (unitMeasurementName == null || unitMeasurementName.isBlank())
            throw new IllegalArgumentException("Unit measurement name is required");
        if (unitMeasurementAbbreviation == null || unitMeasurementAbbreviation.isBlank())
            throw new IllegalArgumentException("Unit measurement abbreviation is required");
        if (userId == null)
            throw new IllegalArgumentException("User ID is required");
        if (categoryId == null)
            throw new IllegalArgumentException("Category ID is required");
    }
}
