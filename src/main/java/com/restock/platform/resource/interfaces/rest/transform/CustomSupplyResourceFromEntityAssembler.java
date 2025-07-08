package com.restock.platform.resource.interfaces.rest.transform;

import com.restock.platform.resource.domain.model.aggregates.CustomSupply;
import com.restock.platform.resource.interfaces.rest.resources.CustomSupplyResource;

public class CustomSupplyResourceFromEntityAssembler {

    public static CustomSupplyResource toResourceFromEntity(CustomSupply customSupply) {
        return new CustomSupplyResource(
                customSupply.getId(),
                customSupply.getSupplyId(),
                customSupply.getDescription(),
                customSupply.getStockRange().minStock(),
                customSupply.getStockRange().maxStock(),
                customSupply.getPrice().amount(),
                customSupply.getPrice().currency().getCurrencyCode(),
                customSupply.getUserId()
        );
    }
}
