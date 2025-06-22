package com.restock.platform.planning.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Embeddable
public class RecipeSupplyId implements Serializable {

    @Column(name = "recipe_id")
    private Long recipeId;

    @Column(name = "supply_id")
    private Long supplyId;

    protected RecipeSupplyId() {}

    public RecipeSupplyId(RecipeId recipeId, CatalogSupplyId supplyId) {
        this.recipeId = recipeId.recipeId();
        this.supplyId = supplyId.value();
    }

    // equals y hashCode obligatorios
}