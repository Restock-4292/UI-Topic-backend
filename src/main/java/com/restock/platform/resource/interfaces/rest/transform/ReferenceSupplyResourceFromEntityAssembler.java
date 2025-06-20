package com.restock.platform.resource.interfaces.rest.transform;

import com.restock.platform.resource.infrastructure.persistence.jpa.entities.ReferenceSupply;
import com.restock.platform.resource.interfaces.rest.resources.ReferenceSupplyResource;

public class ReferenceSupplyResourceFromEntityAssembler {

    public static ReferenceSupplyResource toResourceFromEntity(ReferenceSupply entity) {
        return new ReferenceSupplyResource(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.isPerishable(),
                entity.getUnitName(),
                entity.getUnitAbbreviation(),
                entity.getCategory()
        );
    }
}
