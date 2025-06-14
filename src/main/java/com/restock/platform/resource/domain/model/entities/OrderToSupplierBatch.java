package com.restock.platform.resource.domain.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

public class OrderToSupplierBatch {

    @Id
    @Getter
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Column(nullable = false, updatable = false)
    private Long orderToSupplierId;

    @Getter
    @Column(nullable = false, updatable = false)
    private Long batchId;

    @Getter
    private int quantity;

    @Getter
    @Column(nullable = false)
    private boolean accepted;

    // Constructor
    public OrderToSupplierBatch(Long id, Long orderToSupplierId, Long batchId, int quantity, boolean accepted) {
        this.id = id;
        this.orderToSupplierId = orderToSupplierId;
        this.batchId = batchId;
        this.quantity = quantity;
        this.accepted = accepted;
    }
}
