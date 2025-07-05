package com.restock.platform.resource.domain.model.commands;

/**
 * Command to create an association between an Order and a Batch.
 */
public record CreateOrderBatchCommand(
        Long orderId,
        Long batchId,
        Integer quantity,
        boolean accepted
) {}
