package com.restock.platform.resource.interfaces.rest.transform;

import com.restock.platform.resource.domain.model.aggregates.Supply;
import com.restock.platform.resource.interfaces.rest.resources.SupplyResource;

public class SupplyResourceFromEntityAssembler {

    public static SupplyResource toResourceFromEntity(Supply supply) {
        return new SupplyResource(
                supply.getId(),
                supply.getName(),
                supply.getDescription(),
                supply.isPerishable(),
                supply.getMinStock(),
                supply.getMaxStock(),
                supply.getPrice(),
                supply.getUserId(),
                supply.getUnitMeasurement().getName(),
                supply.getUnitMeasurement().getAbbreviation(),
                supply.getCategory().getId()
        );
    }
}
