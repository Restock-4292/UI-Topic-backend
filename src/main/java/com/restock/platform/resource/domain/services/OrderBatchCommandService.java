package com.restock.platform.resource.domain.services;

import com.restock.platform.resource.domain.model.aggregates.OrderBatch;
import com.restock.platform.resource.domain.model.commands.CreateOrderBatchCommand;

import java.util.Optional;

/**
 * Command service interface for handling order-batch associations.
 */
public interface OrderBatchCommandService {

    /**
     * Handles the creation of an OrderBatch association.
     *
     * @param command the command containing the creation data
     * @return the ID of the newly created OrderBatch
     */
    Long handle(CreateOrderBatchCommand command);

    /**
     * Handles updating the acceptance status of an OrderBatch (optional).
     *
     * @param orderBatchId the ID of the OrderBatch to update
     * @param accepted the new accepted value
     * @return an Optional of the updated OrderBatch
     */
    Optional<OrderBatch> updateAccepted(Long orderBatchId, boolean accepted);

    /**
     * Deletes an existing OrderBatch association (optional).
     *
     * @param orderBatchId the ID of the OrderBatch to delete
     */
    void delete(Long orderBatchId);
}
