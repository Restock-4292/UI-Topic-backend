package com.restock.platform.resource.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record StockRange(
        int minStock,
        int maxStock
) {

    public StockRange {

        if (minStock < 0) {
            throw new IllegalArgumentException("minStock cannot be negative");
        }

        if (maxStock < minStock) {
            throw new IllegalArgumentException("maxStock must be >= minStock");
        }
    }

    public boolean isInRange(int value) {
        return value >= minStock && value <= maxStock;
    }
}