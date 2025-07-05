package com.restock.platform.resource.interfaces.rest.transform;


import com.restock.platform.resource.domain.model.commands.CreateOrderBatchCommand;
import com.restock.platform.resource.interfaces.rest.resources.CreateOrderBatchResource;

public class CreateOrderBatchCommandFromResourceAssembler {
    public static CreateOrderBatchCommand toCommandFromResource(Long orderId, CreateOrderBatchResource resource) {
        return new CreateOrderBatchCommand(orderId, resource.batchId(), resource.quantity(), resource.accepted());
    }
}
