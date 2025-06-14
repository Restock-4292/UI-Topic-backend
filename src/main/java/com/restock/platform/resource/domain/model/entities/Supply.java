package com.restock.platform.resource.domain.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

import java.math.BigDecimal;

public class Supply {

    @Id
    @Getter
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Column(nullable = false)
    private String name;

    @Getter
    private String description;

    @Getter
    private boolean perishable;

    @Getter
    private int minStock;

    @Getter
    private int maxStock;

    @Getter
    @Column(nullable = false, updatable = false)
    private Long categoryId;

    @Getter
    @Column(nullable = false, updatable = false)
    private Long unitMeasurementId;

    @Getter
    private BigDecimal price;

    @Getter
    @Column(nullable = false, updatable = false)
    private Long userId;

    // Constructor
    public Supply(Long id, String name, String description, boolean perishable,
                  int minStock, int maxStock, Long categoryId,
                  Long unitMeasurementId, BigDecimal price, Long userId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.perishable = perishable;
        this.minStock = minStock;
        this.maxStock = maxStock;
        this.categoryId = categoryId;
        this.unitMeasurementId = unitMeasurementId;
        this.price = price;
        this.userId = userId;
    }
}
