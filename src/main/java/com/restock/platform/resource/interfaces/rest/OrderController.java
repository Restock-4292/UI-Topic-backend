package com.restock.platform.resource.interfaces.rest;

import com.restock.platform.resource.domain.model.aggregates.Order;
import com.restock.platform.resource.domain.model.aggregates.OrderBatch;
import com.restock.platform.resource.domain.model.commands.CreateOrderCommand;
import com.restock.platform.resource.domain.model.commands.CreateOrderBatchCommand;
import com.restock.platform.resource.domain.model.commands.UpdateOrderStateCommand;
import com.restock.platform.resource.domain.model.queries.GetAllBatchesByOrderId;
import com.restock.platform.resource.domain.model.queries.GetAllOrdersQuery;
import com.restock.platform.resource.domain.model.queries.GetOrderByIdQuery;
import com.restock.platform.resource.domain.services.OrderBatchCommandService;
import com.restock.platform.resource.domain.services.OrderBatchQueryService;
import com.restock.platform.resource.domain.services.OrderCommandService;
import com.restock.platform.resource.domain.services.OrderQueryService;
import com.restock.platform.resource.interfaces.rest.resources.*;
import com.restock.platform.resource.interfaces.rest.transform.CreateOrderBatchCommandFromResourceAssembler;
import com.restock.platform.resource.interfaces.rest.transform.CreateOrderCommandFromResourceAssembler;
import com.restock.platform.resource.interfaces.rest.transform.OrderBatchResourceFromEntityAssembler;
import com.restock.platform.resource.interfaces.rest.transform.OrderResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/orders", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Orders", description = "Endpoints for managing orders to suppliers and their associated batches")
public class OrderController {

    private final OrderCommandService orderCommandService;
    private final OrderQueryService orderQueryService;
    private final OrderBatchCommandService orderBatchCommandService;
    private final OrderBatchQueryService orderBatchQueryService;

    public OrderController(OrderCommandService orderCommandService,
                           OrderQueryService orderQueryService,
                           OrderBatchCommandService orderBatchCommandService,
                           OrderBatchQueryService orderBatchQueryService) {
        this.orderCommandService = orderCommandService;
        this.orderQueryService = orderQueryService;
        this.orderBatchCommandService = orderBatchCommandService;
        this.orderBatchQueryService = orderBatchQueryService;
    }

    // ------------------- ORDER ------------------------

    @PostMapping
    @Operation(summary = "Create a new order", description = "Create a new order and return its details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Order created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<OrderResource> createOrder(@RequestBody CreateOrderResource resource) {
        var command = CreateOrderCommandFromResourceAssembler.toCommandFromResource(resource);
        var orderId = orderCommandService.handle(command);
        var result = orderQueryService.handle(new GetOrderByIdQuery(orderId));
        if (result.isEmpty()) return ResponseEntity.notFound().build();
        var orderResource = OrderResourceFromEntityAssembler.toResourceFromEntity(result.get());
        return new ResponseEntity<>(orderResource, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all orders", description = "Retrieve all orders")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Orders retrieved successfully")
    })
    public ResponseEntity<List<OrderResource>> getAllOrders() {
        var orders = orderQueryService.handle(new GetAllOrdersQuery());
        var resources = orders.stream()
                .map(OrderResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get order by ID", description = "Retrieve an order by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order found"),
            @ApiResponse(responseCode = "404", description = "Order not found")
    })
    public ResponseEntity<OrderResource> getOrderById(@PathVariable Long id) {
        return orderQueryService.handle(new GetOrderByIdQuery(id))
                .map(OrderResourceFromEntityAssembler::toResourceFromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/state")
    @Operation(summary = "Update order state/situation", description = "Update the state and situation of an order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order updated successfully"),
            @ApiResponse(responseCode = "404", description = "Order not found")
    })
    public ResponseEntity<OrderResource> updateOrderState(
            @PathVariable Long id,
            @RequestBody UpdateOrderStateResource resource) {

        var command = new UpdateOrderStateCommand(id, resource.newState(), resource.newSituation());
        var result = orderCommandService.handle(command);

        if (result.isEmpty()) return ResponseEntity.notFound().build();
        var updatedResource = OrderResourceFromEntityAssembler.toResourceFromEntity(result.get());
        return ResponseEntity.ok(updatedResource);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an order", description = "Delete an order by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Order deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Order not found")
    })
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        try {
            orderCommandService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // ------------------ ORDER BATCH ------------------------

    @PostMapping("/{orderId}/batches")
    @Operation(summary = "Create an order batch", description = "Add a batch to an existing order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Order batch created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<OrderBatchResource> createOrderBatch(
            @PathVariable Long orderId,
            @RequestBody CreateOrderBatchResource resource) {

        var command = CreateOrderBatchCommandFromResourceAssembler.toCommandFromResource(orderId, resource);
        var id = orderBatchCommandService.handle(command);
        var batches = orderBatchQueryService.handle(new GetAllBatchesByOrderId(orderId));

        var batch = batches.stream().filter(b -> b.getId().equals(id)).findFirst();
        if (batch.isEmpty()) return ResponseEntity.notFound().build();

        var response = OrderBatchResourceFromEntityAssembler.toResourceFromEntity(batch.get());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{orderId}/batches")
    @Operation(summary = "Get batches for an order", description = "Retrieve all batches associated with a given order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Batches retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Order not found")
    })
    public ResponseEntity<List<OrderBatchResource>> getBatchesByOrder(@PathVariable Long orderId) {
        var batches = orderBatchQueryService.handle(new GetAllBatchesByOrderId(orderId));
        var resources = batches.stream()
                .map(OrderBatchResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }
}
