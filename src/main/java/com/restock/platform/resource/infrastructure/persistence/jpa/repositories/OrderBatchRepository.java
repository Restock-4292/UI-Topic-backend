package com.restock.platform.resource.infrastructure.persistence.jpa.repositories;

import com.restock.platform.resource.domain.model.aggregates.OrderBatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderBatchRepository extends JpaRepository<OrderBatch, Long> {

    /**
     * Finds all order batch entries by the given order ID.
     *
     * @param orderId ID of the order to filter by
     * @return list of matching OrderBatch entities
     */
    List<OrderBatch> findByOrderId(Long orderId);
}
