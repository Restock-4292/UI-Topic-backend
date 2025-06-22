package com.restock.platform.planning.application.internal.queryservices;

import com.restock.platform.planning.domain.model.aggregates.Recipe;
import com.restock.platform.planning.domain.model.queries.GetRecipeByIdQuery;
import com.restock.platform.planning.domain.services.RecipeQueryService;
import com.restock.platform.planning.infrastructure.persistence.jpa.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecipeQueryServiceImpl implements RecipeQueryService {

    private final RecipeRepository recipeRepository;

    public RecipeQueryServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Optional<Recipe> handle(GetRecipeByIdQuery query) {
        return recipeRepository.findById(query.recipeId());
    }
}
