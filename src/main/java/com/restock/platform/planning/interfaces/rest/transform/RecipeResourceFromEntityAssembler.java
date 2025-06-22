package com.restock.platform.planning.interfaces.rest.transform;

import com.restock.platform.planning.domain.model.aggregates.Recipe;
import com.restock.platform.planning.interfaces.rest.resources.RecipeResource;
import com.restock.platform.planning.interfaces.rest.resources.RecipeSupplyResource;

import java.util.List;

public class RecipeResourceFromEntityAssembler {
    public static RecipeResource toResourceFromEntity(Recipe recipe) {
        List<RecipeSupplyResource> supplies = recipe.getSupplies().stream()
                .map(supply -> new RecipeSupplyResource(
                        supply.getId().getSupplyId(),
                        supply.getSupplyQuantity().supplyQuantity()
                )).toList();

        return new RecipeResource(
                recipe.getRecipeId().recipeId(),
                recipe.getName(),
                recipe.getDescription(),
                recipe.getImageUrl().imageURL(),
                recipe.getTotalPrice().price(),
                recipe.getUserId(),
                supplies
        );
    }
}

