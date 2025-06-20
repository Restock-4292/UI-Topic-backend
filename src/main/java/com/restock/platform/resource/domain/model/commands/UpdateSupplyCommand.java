package com.restock.platform.resource.domain.model.commands;

public record UpdateSupplyCommand(
        Long supplyId,
        String name,
        String description,
        boolean perishable,
        int minStock,
        int maxStock,
        double price,
        String unitMeasurementName,
        String unitMeasurementAbbreviation,
        Long categoryId
) {
    public UpdateSupplyCommand {
        if (supplyId == null || supplyId <= 0)
            throw new IllegalArgumentException("Supply ID must be a positive number");
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Name cannot be null or blank");
        if (description == null || description.isBlank())
            throw new IllegalArgumentException("Description cannot be null or blank");
        if (minStock < 0)
            throw new IllegalArgumentException("Minimum stock cannot be negative");
        if (maxStock < minStock)
            throw new IllegalArgumentException("Maximum stock must be greater than or equal to minimum stock");
        if (price < 0)
            throw new IllegalArgumentException("Price cannot be negative");
        if (unitMeasurementName == null || unitMeasurementName.isBlank())
            throw new IllegalArgumentException("Unit name is required");
        if (unitMeasurementAbbreviation == null || unitMeasurementAbbreviation.isBlank())
            throw new IllegalArgumentException("Unit abbreviation is required");
        if (categoryId == null || categoryId <= 0)
            throw new IllegalArgumentException("Category ID must be a positive number");
    }
}
