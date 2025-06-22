package com.restock.platform.planning.application.internal.commandservices;

import com.restock.platform.planning.domain.model.aggregates.Recipe;
import com.restock.platform.planning.domain.model.commands.CreateRecipeCommand;
import com.restock.platform.planning.domain.model.valueobjects.*;
import com.restock.platform.planning.domain.services.RecipeCommandService;
import com.restock.platform.planning.infrastructure.persistence.jpa.repositories.RecipeRepository;
import org.springframework.stereotype.Service;


@Service
public class RecipeCommandServiceImpl implements RecipeCommandService {

    private final RecipeRepository recipeRepository;

    public RecipeCommandServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Long handle(CreateRecipeCommand command) {
        var recipe = new Recipe(
                command.name(),
                command.description(),
                new RecipeImageURL(command.imageUrl()),
                new RecipePrice(command.price()),
                command.userId()
        );

        command.supplies().forEach(supply -> {
            var supplyId = new CatalogSupplyId(supply.supplyId());
            var quantity = new RecipeSupplyQuantity(supply.quantity());
            recipe.addSupply(supplyId, quantity);
        });

        try {
            recipeRepository.save(recipe);
        } catch (Exception e) {
            throw new RuntimeException("Error saving recipe: " + e.getMessage(), e);
        }

        return recipe.getId();
    }
}
