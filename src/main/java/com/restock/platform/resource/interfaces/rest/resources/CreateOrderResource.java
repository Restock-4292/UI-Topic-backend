package com.restock.platform.resource.interfaces.rest.resources;

import com.restock.platform.resource.domain.model.valueobjects.OrderToSupplierSituation;
import com.restock.platform.resource.domain.model.valueobjects.OrderToSupplierState;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateOrderResource(
        Long adminRestaurantId,
        Long supplierId,
        LocalDate date,
        Integer requestedProductsCount,
        BigDecimal totalPrice
) {
}
