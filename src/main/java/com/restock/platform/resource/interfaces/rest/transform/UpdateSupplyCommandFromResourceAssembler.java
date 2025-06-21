package com.restock.platform.resource.interfaces.rest.transform;

import com.restock.platform.resource.domain.model.commands.UpdateSupplyCommand;
import com.restock.platform.resource.domain.model.valueobjects.Price;
import com.restock.platform.resource.domain.model.valueobjects.StockRange;
import com.restock.platform.resource.interfaces.rest.resources.UpdateSupplyResource;

import java.util.Currency;

public class UpdateSupplyCommandFromResourceAssembler {
    public static UpdateSupplyCommand toCommandFromResource(Long supplyId, UpdateSupplyResource resource) {
        return new UpdateSupplyCommand(
                supplyId,
                resource.description(),
                new StockRange(resource.minStock(), resource.maxStock()),
                new Price(resource.price(), Currency.getInstance("USD"))
        );
    }
}
