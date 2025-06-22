package com.restock.platform.planning.infrastructure.persistence.jpa.repositories;

import com.restock.platform.planning.domain.model.aggregates.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

}
