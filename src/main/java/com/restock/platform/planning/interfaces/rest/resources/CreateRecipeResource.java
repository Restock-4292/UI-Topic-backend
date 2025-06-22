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
    public List<RecipeSupplyResource> getSuppliesOrEmpty() {
        return supplies == null ? List.of() : supplies;
    }
}