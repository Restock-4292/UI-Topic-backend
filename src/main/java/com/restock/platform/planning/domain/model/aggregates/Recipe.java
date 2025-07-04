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

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
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

    public void addSupply(RecipeId recipeId, CatalogSupplyId supplyId, RecipeSupplyQuantity quantity) {
        var existing = supplies.stream()
                .filter(s -> s.getId().getSupplyId().equals(supplyId.value()))
                .findFirst();

        if (existing.isPresent()) {
            throw new IllegalArgumentException("Supply already exists in recipe");
        }

        var supply = new RecipeSupply(this, recipeId, supplyId, quantity);
        supplies.add(supply);
    }


    public Recipe update(String name, String description, RecipeImageURL imageUrl, RecipePrice totalPrice) {
        if (name != null && !name.isBlank()) this.name = name;
        if (description != null && !description.isBlank()) this.description = description;
        if (imageUrl != null) this.imageUrl = imageUrl;
        if (totalPrice != null) this.totalPrice = totalPrice;
        return this;
    }

    public void updateSupply(CatalogSupplyId supplyId, RecipeSupplyQuantity quantity) {
        this.supplies.stream()
                .filter(s -> s.getId().getSupplyId().equals(supplyId.value()))
                .findFirst()
                .ifPresent(s -> s.updateQuantity(quantity));
    }

    public void removeSupply(CatalogSupplyId supplyId) {
        this.supplies.removeIf(s -> s.getId().getSupplyId().equals(supplyId.value()));
    }

    public void clearSupplies() {
        this.supplies.clear();
    }
}
