package com.restock.platform.planning.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
public record CatalogSupplyId(@Column(name = "supply_id") Long value) {
    public CatalogSupplyId {
        if (value == null || value <= 0)
            throw new IllegalArgumentException("Supply ID must be a positive number.");
    }
}

