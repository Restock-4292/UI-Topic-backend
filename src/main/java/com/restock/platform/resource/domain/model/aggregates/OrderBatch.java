package com.restock.platform.resource.domain.model.aggregates;

import com.restock.platform.resource.domain.model.aggregates.Batch;
import com.restock.platform.resource.domain.model.aggregates.Order;
import com.restock.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

/**
 * Represents a Batch included in an Order to Supplier.
 */
@Entity
public class OrderBatch extends AuditableAbstractAggregateRoot<OrderBatch> {

    @Getter
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Getter
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "batch_id")
    private Batch batch;

    @Getter
    private Integer quantity;

    @Getter
    private boolean accepted;

    protected OrderBatch() {
        // For JPA
    }

    public OrderBatch(Order order, Batch batch, Integer quantity, boolean accepted) {
        this.order = order;
        this.batch = batch;
        this.quantity = quantity;
        this.accepted = accepted;
    }

    public OrderBatch updateQuantity(Integer quantity) {
        if (quantity != null) this.quantity = quantity;
        return this;
    }

    public OrderBatch markAsAccepted() {
        this.accepted = true;
        return this;
    }
}
