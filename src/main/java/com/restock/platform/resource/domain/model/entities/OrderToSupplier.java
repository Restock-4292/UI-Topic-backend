package com.restock.platform.resource.domain.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class OrderToSupplier {

    @Id
    @Getter
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Getter
    @CreatedDate
    private LocalDate date;

    @Getter
    @CreatedDate
    private LocalDate estimatedShipDate;

    @Getter
    @CreatedDate
    private LocalTime estimatedShipTime;

    @Getter
    private String description;

    @Getter
    @Column(nullable = false, updatable = false)
    private Long adminRestaurantId;

    @Getter
    @Column(nullable = false, updatable = false)
    private Long supplierId;

    @Getter
    @Column(nullable = false)
    private Long orderToSupplierStateId;

    @Getter
    @Column(nullable = false)
    private Long orderToSupplierSituationId;

    @Getter
    private int requestedProductsCount;

    @Getter
    private BigDecimal totalPrice;

    @Getter
    @Column(nullable = false)
    private boolean partiallyAccepted;

    public OrderToSupplier(Long id,
                           LocalDate date,
                           LocalDate estimatedShipDate,
                           LocalTime estimatedShipTime,
                           String description,
                           Long adminRestaurantId,
                           Long supplierId,
                           Long orderToSupplierStateId,
                           Long orderToSupplierSituationId,
                           int requestedProductsCount,
                           BigDecimal totalPrice,
                           boolean partiallyAccepted) {
        this.id = id;
        this.date = date;
        this.estimatedShipDate = estimatedShipDate;
        this.estimatedShipTime = estimatedShipTime;
        this.description = description;
        this.adminRestaurantId = adminRestaurantId;
        this.supplierId = supplierId;
        this.orderToSupplierStateId = orderToSupplierStateId;
        this.orderToSupplierSituationId = orderToSupplierSituationId;
        this.requestedProductsCount = requestedProductsCount;
        this.totalPrice = totalPrice;
        this.partiallyAccepted = partiallyAccepted;
    }

}
