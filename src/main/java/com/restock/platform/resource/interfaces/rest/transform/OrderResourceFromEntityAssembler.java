package com.restock.platform.resource.interfaces.rest.transform;

import com.restock.platform.resource.domain.model.aggregates.Order;
import com.restock.platform.resource.interfaces.rest.resources.OrderResource;

public class OrderResourceFromEntityAssembler {
    public static OrderResource toResourceFromEntity(Order order) {
        return new OrderResource(
                order.getId(),
                order.getAdminRestaurantId(),
                order.getSupplierId(),
                order.getDate(),
                order.getRequestedProductsCount(),
                order.getTotalPrice(),
                order.isPartiallyAccepted(),
                order.getState(),
                order.getSituation()
        );
    }
}
