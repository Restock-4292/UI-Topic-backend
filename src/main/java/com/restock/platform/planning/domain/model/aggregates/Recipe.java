package com.restock.platform.planning.domain.model.aggregates;

import com.restock.platform.planning.domain.model.entities.RecipeSupply;
import com.restock.platform.planning.domain.model.valueobjects.*;
import com.restock.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Recipe extends AuditableAbstractAggregateRoot<Recipe> {

    private String name;
    private String description;

    @Embedded
    private RecipeImageURL imageUrl;
    @Embedded
    private RecipePrice totalPrice;

    @Column(name = "user_id")
    private Integer userId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecipeSupply> supplies = new ArrayList<>();

    protected Recipe() {}

    public Recipe(String name, String description, RecipeImageURL imageUrl, RecipePrice totalPrice, Integer userId) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.totalPrice = totalPrice;
        this.userId = userId;
    }

    public RecipeId getRecipeId() {
        return new RecipeId(super.getId());
    }

    public void addSupply(CatalogSupplyId supplyId, RecipeSupplyQuantity quantity) {
        var recipeSupply = new RecipeSupply(getRecipeId(), supplyId, quantity);
        this.supplies.add(recipeSupply);
    }

    public void clearSupplies() {
        this.supplies.clear();
    }}
