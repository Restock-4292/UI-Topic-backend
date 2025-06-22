package com.restock.platform.planning.domain.services;

import com.restock.platform.planning.domain.model.aggregates.Recipe;
import com.restock.platform.planning.domain.model.commands.CreateRecipeCommand;

import java.util.Optional;

public interface RecipeCommandService {
    Long handle(CreateRecipeCommand command);
}
