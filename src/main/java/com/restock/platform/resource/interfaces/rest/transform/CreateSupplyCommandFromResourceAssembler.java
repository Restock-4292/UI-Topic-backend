package com.restock.platform.resource.interfaces.rest.transform;

import com.restock.platform.resource.domain.model.commands.CreateSupplyCommand;
import com.restock.platform.resource.interfaces.rest.resources.CreateSupplyResource;

public class CreateSupplyCommandFromResourceAssembler {
    public static CreateSupplyCommand toCommandFromResource(CreateSupplyResource resource) {
        return new CreateSupplyCommand(
                resource.name(),
                resource.description(),
                resource.perishable(),
                resource.minStock(),
                resource.maxStock(),
                resource.price(),
                resource.userId(),
                resource.unitMeasurementName(),
                resource.unitMeasurementAbbreviation(),
                resource.categoryId()
        );
    }
}