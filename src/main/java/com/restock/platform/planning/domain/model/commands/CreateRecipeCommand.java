package com.restock.platform.planning.domain.model.commands;

import com.restock.platform.planning.interfaces.rest.resources.RecipeSupplyResource;

import java.util.List;

public record CreateRecipeCommand(
        String name,
        String description,
        String imageUrl,
        Double price,
        Integer userId,
        List<RecipeSupplyResource> supplies
) {
}
