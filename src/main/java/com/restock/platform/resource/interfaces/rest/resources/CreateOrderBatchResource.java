package com.restock.platform.resource.interfaces.rest.resources;

public record CreateOrderBatchResource(
        Long batchId,
        Integer quantity,
        boolean accepted
) {}