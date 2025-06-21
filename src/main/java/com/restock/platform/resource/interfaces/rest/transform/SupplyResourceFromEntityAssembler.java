package com.restock.platform.resource.interfaces.rest.transform;

import com.restock.platform.resource.domain.model.aggregates.Supply;
import com.restock.platform.resource.interfaces.rest.resources.SupplyResource;

public class SupplyResourceFromEntityAssembler {

    public static SupplyResource toResourceFromEntity(Supply supply) {
        return new SupplyResource(
                supply.getId(),
                supply.getReferenceSupplyId(),
                supply.getDescription(),
                supply.getStockRange().minStock(),
                supply.getStockRange().maxStock(),
                supply.getPrice().amount(),
                supply.getPrice().currency().getCurrencyCode(),
                supply.getUserId()
        );
    }
}
