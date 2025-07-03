package com.restock.platform.resource.application.internal.commandservices;

import com.restock.platform.resource.domain.model.aggregates.Order;
import com.restock.platform.resource.domain.model.commands.CreateOrderCommand;
import com.restock.platform.resource.domain.model.commands.UpdateOrderStateCommand;
import com.restock.platform.resource.domain.services.OrderCommandService;
import com.restock.platform.resource.infrastructure.persistence.jpa.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderCommandServiceImpl implements OrderCommandService {

    private final OrderRepository orderRepository;

    public OrderCommandServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Long handle(CreateOrderCommand command) {
        var order = new Order(command);
        try {
            orderRepository.save(order);
            return order.getId();
        } catch (Exception e) {
            throw new RuntimeException("Error saving order: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<Order> handle(UpdateOrderStateCommand command) {
        var order = orderRepository.findById(command.orderId())
                .orElseThrow(() -> new IllegalArgumentException("Order not found with id: " + command.orderId()));

        order.updateState(command.newState(), command.newSituation());

        orderRepository.save(order);
        return Optional.of(order);
    }

    @Override
    public void delete(Long orderId) {
        if (!orderRepository.existsById(orderId)) {
            throw new IllegalArgumentException("Order not found with id: " + orderId);
        }
        try {
            orderRepository.deleteById(orderId);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting order: " + e.getMessage(), e);
        }
    }
}
