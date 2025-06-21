package com.restock.platform.planning.domain.model.entities;

import com.restock.platform.planning.domain.model.valueobjects.RecipeId;
import com.restock.platform.planning.domain.model.valueobjects.RecipeSupplyQuantity;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@Getter
public class RecipeSupply {
    @Embedded
    private RecipeId recipeId;

    @Embedded SupplyId supplyId;

    @Embedded
    private RecipeSupplyQuantity supplyQuantity;

    public RecipeSupply(RecipeId recipeId, SupplyId supplyId, RecipeSupplyQuantity supplyQuantity) {
        this.recipeId = recipeId;
        this.supplyId = supplyId;
        this.supplyQuantity = supplyQuantity;
    }
}
