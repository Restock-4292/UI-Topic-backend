package com.restock.platform.resource.infrastructure.persistence.jpa.entities;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "reference_supplies")
@Getter
public class ReferenceSupply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private boolean perishable;
    private String unitName;
    private String unitAbbreviation;
    private String category;

    protected ReferenceSupply() {} // JPA

    public ReferenceSupply(String name, String description, boolean perishable,
                           String unitName, String unitAbbreviation, String category) {
        this.name = name;
        this.description = description;
        this.perishable = perishable;
        this.unitName = unitName;
        this.unitAbbreviation = unitAbbreviation;
        this.category = category;
    }
}
