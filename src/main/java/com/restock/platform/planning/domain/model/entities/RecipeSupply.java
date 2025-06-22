package com.restock.platform.planning.domain.model.entities;

import com.restock.platform.planning.domain.model.valueobjects.CatalogSupplyId;
import com.restock.platform.planning.domain.model.valueobjects.RecipeId;
import com.restock.platform.planning.domain.model.valueobjects.RecipeSupplyId;
import com.restock.platform.planning.domain.model.valueobjects.RecipeSupplyQuantity;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "recipe_supplies")
@Getter
public class RecipeSupply {

    @EmbeddedId
    private RecipeSupplyId id;

    @Embedded
    private RecipeSupplyQuantity supplyQuantity;

    protected RecipeSupply() {}

    public RecipeSupply(RecipeId recipeId, CatalogSupplyId supplyId, RecipeSupplyQuantity quantity) {
        this.id = new RecipeSupplyId(recipeId, supplyId);
        this.supplyQuantity = quantity;
    }

    public void updateQuantity(RecipeSupplyQuantity supplyQuantity) {
        this.supplyQuantity = supplyQuantity;
    }
}
