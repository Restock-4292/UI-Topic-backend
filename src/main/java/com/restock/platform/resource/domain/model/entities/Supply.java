package com.restock.platform.resource.domain.model.entities;

import com.restock.platform.resource.domain.model.valueobjects.UnitMeasurement;
import com.restock.platform.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;

/*
        "name": "Pollo Entero",
        "description": "Pollo fresco sin vísceras",
        "perishable": true,
        "unitName": "Unidad",
        "unitAbbreviation": "u",
        "category": "Carnes"
 */
@Entity
public class Supply extends AuditableModel {

    @Getter
    private String name;

    @Getter
    private String description;

    @Getter
    private Boolean perishable;

    @Getter
    @Embedded
    private UnitMeasurement unitMeasurement;

    @Getter
    private String category;

    protected Supply() {
        // Para JPA
    }

    public Supply(String name, String description, Boolean perishable, UnitMeasurement unitMeasurement, String category) {
        this.name = name;
        this.description = description;
        this.perishable = perishable;
        this.unitMeasurement = unitMeasurement;
        this.category = category;
    }
}
