package com.restock.platform.resource.domain.model.entities;

import jakarta.persistence.Entity;
import com.restock.platform.shared.domain.model.entities.AuditableModel;
import lombok.Getter;

@Getter
@Entity
public class Category extends AuditableModel {
    private String name;

    protected Category() {}

    public Category(String name) {
        this.name = name;
    }

    public Category updateName(String name) {
        if (name != null && !name.isBlank()) {
            this.name = name;
        }
        return this;
    }
}
