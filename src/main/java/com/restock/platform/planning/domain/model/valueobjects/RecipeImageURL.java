package com.restock.platform.planning.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record RecipeImageURL(String imageURL)
{
    public RecipeImageURL {
        if (imageURL == null || imageURL.isBlank()) {
            throw new IllegalArgumentException("Image URL must not be null or blank");
        }
    }
}
