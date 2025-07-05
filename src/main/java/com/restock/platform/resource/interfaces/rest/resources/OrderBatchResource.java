package com.restock.platform.resource.interfaces.rest.resources;

import com.restock.platform.resource.domain.model.aggregates.Batch;
import com.restock.platform.resource.domain.model.aggregates.Order;

public record OrderBatchResource(Long id,Order order, Batch batch, Integer quantity, boolean accepted) {
}
