package com.restock.platform.planning.interfaces.rest.resources;

import java.util.List;

public record CreateRecipeResource(
        String name,
        String description,
        String imageUrl,
        Double totalPrice,
        int userId,
        List<RecipeSupplyResource> supplies
) {
    public CreateRecipeResource{
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name cannot be null or blank");
        if (description == null || description.isBlank()) throw new IllegalArgumentException("Description cannot be null or blank");
        if (imageUrl == null || imageUrl.isBlank()) throw new IllegalArgumentException("Image URL cannot be null or blank");
        if (totalPrice == null || totalPrice < 0) throw new IllegalArgumentException("Total price must be a non-negative number");
        if (userId <= 0) throw new IllegalArgumentException("User ID must be a positive number");
        if (supplies == null || supplies.isEmpty()) throw new IllegalArgumentException("Supplies cannot be null or empty");
    }
}
