package com.restock.platform.resource.infrastructure.persistence.jpa.entities;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "reference_categories")
@Getter
public class ReferenceCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    protected ReferenceCategory() {} // JPA

    public ReferenceCategory(String name) {
        this.name = name;
    }
}
