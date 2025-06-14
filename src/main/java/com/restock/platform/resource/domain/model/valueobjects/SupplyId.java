package com.restock.platform.resource.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

/// <summary>
/// Represents a unique identifier for a supply.
/// </summary>
/// <param name="Id">
/// The unique identifier for the supply.
/// </param>

@Embeddable
public record SupplyId(Long supplyId)
{
    public SupplyId {
        if (supplyId == null || supplyId <= 0) {
            throw new IllegalArgumentException("Supply ID must be a positive number.");
        }
    }
}
