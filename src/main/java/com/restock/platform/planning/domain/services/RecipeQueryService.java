package com.restock.platform.planning.domain.services;

import com.restock.platform.planning.domain.model.aggregates.Recipe;
import com.restock.platform.planning.domain.model.queries.GetRecipeByIdQuery;

import java.util.Optional;

public interface RecipeQueryService {
    Optional<Recipe> handle(GetRecipeByIdQuery query);
}