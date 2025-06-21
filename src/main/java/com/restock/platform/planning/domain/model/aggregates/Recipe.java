package com.restock.platform.planning.domain.model.aggregates;

import com.restock.platform.planning.domain.model.valueobjects.RecipeId;
import com.restock.platform.planning.domain.model.valueobjects.RecipeImageURL;
import com.restock.platform.planning.domain.model.valueobjects.RecipePrice;
import com.restock.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
public class Recipe extends AuditableAbstractAggregateRoot<Recipe> {

    @Embedded
    @Column(name = "recipe_id")
    private RecipeId id;

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

    private Recipe(RecipeId id, String name, String description, RecipeImageURL imageUrl, RecipePrice totalPrice, Integer userId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.totalPrice = totalPrice;
        this.userId = userId;
    }

    public void addSupply(RecipeSupply supply) {
        this.supplies.add(supply);
    }

    public void removeAllSupplies() {
        this.supplies.clear();
    }
}
