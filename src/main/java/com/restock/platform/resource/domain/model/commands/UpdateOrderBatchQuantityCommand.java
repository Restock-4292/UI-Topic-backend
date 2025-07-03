package com.restock.platform.resource.domain.model.commands;

public record UpdateOrderBatchQuantityCommand(
        Long orderBatchId,
        Integer newQuantity
) {}
