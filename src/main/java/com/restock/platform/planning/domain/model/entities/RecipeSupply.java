package com.restock.platform.planning.domain.model.entities;

import com.restock.platform.planning.domain.model.aggregates.Recipe;
import com.restock.platform.planning.domain.model.valueobjects.CatalogSupplyId;
import com.restock.platform.planning.domain.model.valueobjects.RecipeId;
import com.restock.platform.planning.domain.model.valueobjects.RecipeSupplyId;
import com.restock.platform.planning.domain.model.valueobjects.RecipeSupplyQuantity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "recipe_supplies")
@Getter
public class RecipeSupply {

    @EmbeddedId
    private RecipeSupplyId id;

    @Embedded
    private RecipeSupplyQuantity supplyQuantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("recipeId")
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    protected RecipeSupply() {}

    public RecipeSupply(Recipe recipe, RecipeId recipeId, CatalogSupplyId supplyId, RecipeSupplyQuantity quantity) {
        this.recipe = recipe;
        this.id = new RecipeSupplyId(recipeId, supplyId);
        this.supplyQuantity = quantity;
    }

    public void updateQuantity(RecipeSupplyQuantity supplyQuantity) {
        this.supplyQuantity = supplyQuantity;
    }
}
