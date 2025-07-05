package com.restock.platform.resource.domain.services;

import com.restock.platform.resource.domain.model.aggregates.OrderBatch;
import com.restock.platform.resource.domain.model.queries.GetAllBatchesByOrderId;

import java.util.List;

/**
 * Query service interface for retrieving OrderBatch entities.
 */
public interface OrderBatchQueryService {

    /**
     * Retrieves all OrderBatch records associated with a specific order.
     *
     * @param query query containing the target order ID
     * @return list of OrderBatch entries related to the order
     */
    List<OrderBatch> handle(GetAllBatchesByOrderId query);

}
