package com.restock.platform.profiles.domain.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "business_categories")
public class BusinessCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    protected BusinessCategory() {
        // JPA requires a default constructor
    }

    public BusinessCategory(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
