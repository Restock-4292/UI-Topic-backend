package com.restock.platform.resource.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import java.util.Objects;

/**
 * Value Object representing a unit of measurement.
 */
@Embeddable
public record UnitMeasurement(String name, String abbreviation) {

    public UnitMeasurement {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Unit name cannot be null or blank");
        }
        if (abbreviation == null || abbreviation.isBlank()) {
            throw new IllegalArgumentException("Unit abbreviation cannot be null or blank");
        }
    }

    public String getName() {
        return name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UnitMeasurement that)) return false;
        return name.equalsIgnoreCase(that.name) &&
                abbreviation.equalsIgnoreCase(that.abbreviation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name.toLowerCase(), abbreviation.toLowerCase());
    }
}
