package com.restock.platform.resource.domain.model.entities;

import com.restock.platform.resource.domain.model.valueobjects.UnitMeasurement;
import com.restock.platform.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.Embedded;
import lombok.Getter;

/*
        "name": "Pollo Entero",
        "description": "Pollo fresco sin vísceras",
        "perishable": true,
        "unitName": "Unidad",
        "unitAbbreviation": "u",
        "category": "Carnes"
 */

public class Supply extends AuditableModel {

    @Getter
    @Embedded
    private String name;

    @Getter
    @Embedded
    private String description;

    @Getter
    @Embedded
    private Boolean perishable;

    @Getter
    @Embedded
    private UnitMeasurement unitMeasurement;

    @Getter
    private String category;

    protected Supply() {
        // Para JPA
    }
}
