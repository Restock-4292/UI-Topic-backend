package com.restock.platform.resource.interfaces.rest.transform;

import com.restock.platform.resource.domain.model.commands.CreateOrderCommand;
import com.restock.platform.resource.domain.model.valueobjects.Price;
import com.restock.platform.resource.domain.model.valueobjects.StockRange;
import com.restock.platform.resource.interfaces.rest.resources.CreateOrderResource;

import java.util.Currency;

public class CreateOrderCommandFromResourceAssembler {
    public static CreateOrderCommand toCommandFromResource(CreateOrderResource resource) {
        return new CreateOrderCommand(
                resource.adminRestaurantId(),
                resource.supplierId(),
                resource.date(),
                resource.requestedProductsCount(),
                resource.totalPrice()
        );
    }
}



