package com.restock.platform.profiles.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

/**
 * Value Object that represents a User's unique identifier.
 */
@Embeddable
public class UserId {

    @Column(name = "user_id")
    private int value;

    protected UserId() {
        this.value = 0;
    }

    public UserId(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("UserId must be a positive number greater than 0.");
        }
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserId)) return false;
        UserId userId = (UserId) o;
        return value == userId.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
