package com.restock.platform.resource.interfaces.rest.transform;

import com.restock.platform.resource.domain.model.commands.CreateSupplyCommand;
import com.restock.platform.resource.domain.model.valueobjects.StockRange;
import com.restock.platform.resource.domain.model.valueobjects.Price;
import com.restock.platform.resource.interfaces.rest.resources.CreateSupplyResource;

import java.util.Currency;

public class CreateSupplyCommandFromResourceAssembler {
    public static CreateSupplyCommand toCommandFromResource(CreateSupplyResource resource) {
        return new CreateSupplyCommand(
                resource.referenceSupplyId(),
                new StockRange(resource.minStock(), resource.maxStock()),
                new Price(resource.price(), Currency.getInstance("USD")),  // ← Usamos USD por defecto
                resource.description(),
                resource.userId()
        );
    }
}
