package com.restock.platform.resource.application.internal.commandservices;

import com.restock.platform.resource.domain.model.aggregates.Batch;
import com.restock.platform.resource.domain.model.aggregates.Order;
import com.restock.platform.resource.domain.model.aggregates.OrderBatch;
import com.restock.platform.resource.domain.model.commands.CreateOrderBatchCommand;
import com.restock.platform.resource.domain.services.OrderBatchCommandService;
import com.restock.platform.resource.infrastructure.persistence.jpa.repositories.BatchRepository;
import com.restock.platform.resource.infrastructure.persistence.jpa.repositories.OrderBatchRepository;
import com.restock.platform.resource.infrastructure.persistence.jpa.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderBatchCommandServiceImpl implements OrderBatchCommandService {

    private final OrderBatchRepository orderBatchRepository;
    private final OrderRepository orderRepository;
    private final BatchRepository batchRepository;

    public OrderBatchCommandServiceImpl(
            OrderBatchRepository orderBatchRepository,
            OrderRepository orderRepository,
            BatchRepository batchRepository
    ) {
        this.orderBatchRepository = orderBatchRepository;
        this.orderRepository = orderRepository;
        this.batchRepository = batchRepository;
    }

    @Override
    public Long handle(CreateOrderBatchCommand command) {
        Order order = orderRepository.findById(command.orderId())
                .orElseThrow(() -> new IllegalArgumentException("Order not found with ID: " + command.orderId()));

        Batch batch = batchRepository.findById(command.batchId())
                .orElseThrow(() -> new IllegalArgumentException("Batch not found with ID: " + command.batchId()));

        var orderBatch = new OrderBatch(order, batch, command.quantity(), command.accepted());

        try {
            orderBatchRepository.save(orderBatch);
            return orderBatch.getId();
        } catch (Exception e) {
            throw new RuntimeException("Error saving OrderBatch: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<OrderBatch> updateAccepted(Long orderBatchId, boolean accepted) {
        var orderBatch = orderBatchRepository.findById(orderBatchId)
                .orElseThrow(() -> new IllegalArgumentException("OrderBatch not found with ID: " + orderBatchId));

        orderBatch.markAsAccepted();

        orderBatchRepository.save(orderBatch);
        return Optional.of(orderBatch);
    }

    @Override
    public void delete(Long orderBatchId) {
        if (!orderBatchRepository.existsById(orderBatchId)) {
            throw new IllegalArgumentException("OrderBatch not found with ID: " + orderBatchId);
        }
        orderBatchRepository.deleteById(orderBatchId);
    }
}
