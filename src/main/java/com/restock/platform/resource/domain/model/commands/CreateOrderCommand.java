package com.restock.platform.resource.domain.model.commands;

import com.restock.platform.resource.domain.model.valueobjects.OrderToSupplierSituation;
import com.restock.platform.resource.domain.model.valueobjects.OrderToSupplierState;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Command to create a new Order.
 *
 * Represents the necessary data to register a new order from an admin restaurant to a supplier.
 */
public record CreateOrderCommand(
        Long adminRestaurantId,
        Long supplierId,
        LocalDate date,
        Integer requestedProductsCount,
        BigDecimal totalPrice
) {}
