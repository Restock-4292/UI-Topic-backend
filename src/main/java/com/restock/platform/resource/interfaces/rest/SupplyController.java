package com.restock.platform.resource.interfaces.rest;

import com.restock.platform.resource.domain.model.commands.CreateSupplyCommand;
import com.restock.platform.resource.domain.model.commands.DeleteSupplyCommand;
import com.restock.platform.resource.domain.model.queries.GetAllSuppliesQuery;
import com.restock.platform.resource.domain.model.queries.GetSupplyByIdQuery;
import com.restock.platform.resource.domain.model.queries.GetSuppliesByUserIdQuery;
import com.restock.platform.resource.domain.services.SupplyCommandService;
import com.restock.platform.resource.domain.services.SupplyQueryService;
import com.restock.platform.resource.interfaces.rest.resources.CreateSupplyResource;
import com.restock.platform.resource.interfaces.rest.resources.SupplyResource;
import com.restock.platform.resource.interfaces.rest.resources.UpdateSupplyResource;
import com.restock.platform.resource.interfaces.rest.transform.CreateSupplyCommandFromResourceAssembler;
import com.restock.platform.resource.interfaces.rest.transform.SupplyResourceFromEntityAssembler;
import com.restock.platform.resource.interfaces.rest.transform.UpdateSupplyCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * SupplyController
 * <p>
 *     All supply-related endpoints.
 * </p>
 */
@RestController
@RequestMapping(value = "/api/v1/supplies", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Supplies", description = "Endpoints for managing supplies")
public class SupplyController {

    private final SupplyCommandService supplyCommandService;
    private final SupplyQueryService supplyQueryService;

    /**
     * Constructor
     *
     * @param supplyCommandService The {@link SupplyCommandService} instance
     * @param supplyQueryService   The {@link SupplyQueryService} instance
     */
    public SupplyController(SupplyCommandService supplyCommandService, SupplyQueryService supplyQueryService) {
        this.supplyCommandService = supplyCommandService;
        this.supplyQueryService = supplyQueryService;
    }

    /**
     * Create a new supply
     *
     * @param resource The {@link CreateSupplyResource} instance
     * @return The created {@link SupplyResource}
     */
    @PostMapping
    @Operation(summary = "Create a new supply", description = "Create a new supply and return its details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Supply created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<SupplyResource> createSupply(@RequestBody CreateSupplyResource resource) {
        var createSupplyCommand = CreateSupplyCommandFromResourceAssembler.toCommandFromResource(resource);
        var supplyId = supplyCommandService.handle(createSupplyCommand);
        if (supplyId == null || supplyId == 0L) return ResponseEntity.badRequest().build();
        var getSupplyByIdQuery = new GetSupplyByIdQuery(supplyId);
        var supply = supplyQueryService.handle(getSupplyByIdQuery);
        if (supply.isEmpty()) return ResponseEntity.notFound().build();
        var supplyEntity = supply.get();
        var supplyResource = SupplyResourceFromEntityAssembler.toResourceFromEntity(supplyEntity);
        return new ResponseEntity<>(supplyResource, HttpStatus.CREATED);
    }

    /**
     * Get all supplies
     *
     * @return A list of {@link SupplyResource}
     */
    @GetMapping
    @Operation(summary = "Get all supplies", description = "Retrieve all available supplies")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Supplies retrieved successfully")
    })
    public ResponseEntity<List<SupplyResource>> getAllSupplies() {
        var supplies = supplyQueryService.handle(new GetAllSuppliesQuery());
        var resources = supplies.stream()
                .map(SupplyResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    /**
     * Get supply by ID
     *
     * @param id The ID of the supply
     * @return The {@link SupplyResource} if found
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get supply by ID", description = "Retrieve a supply by its unique ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Supply found"),
            @ApiResponse(responseCode = "404", description = "Supply not found")
    })
    public ResponseEntity<SupplyResource> getSupplyById(@PathVariable Long id) {
        return supplyQueryService.handle(new GetSupplyByIdQuery(id))
                .map(SupplyResourceFromEntityAssembler::toResourceFromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    /**
     * Delete a supply by ID
     *
     * @param id The ID of the supply to delete
     * @return 204 No Content if deleted successfully
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a supply", description = "Delete a supply by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Supply deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Supply not found")
    })
    public ResponseEntity<Void> deleteSupplyById(@PathVariable Long id) {
        try {
            supplyCommandService.handle(new DeleteSupplyCommand(id));
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Get supplies by user ID
     *
     * @param userId The ID of the user
     * @return A list of {@link SupplyResource} belonging to the user
     */
    @GetMapping("/user/{userId}")
    @Operation(summary = "Get supplies by user ID", description = "Retrieve all supplies associated with a given user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Supplies found for user")
    })
    public ResponseEntity<List<SupplyResource>> getSuppliesByUserId(@PathVariable Long userId) {
        var supplies = supplyQueryService.handle(new GetSuppliesByUserIdQuery(userId));
        var resources = supplies.stream()
                .map(SupplyResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    /**
     * Update a supply by ID
     *
     * @param id The ID of the supply
     * @param resource The updated values
     * @return The updated {@link SupplyResource}
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update a supply", description = "Update an existing supply by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Supply updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Supply not found")
    })
    public ResponseEntity<SupplyResource> updateSupply(
            @PathVariable Long id,
            @RequestBody UpdateSupplyResource resource) {

        var command = UpdateSupplyCommandFromResourceAssembler.toCommandFromResource(id, resource);
        var updatedSupply  = supplyCommandService.handle(command);

        if (updatedSupply.isEmpty()) return ResponseEntity.notFound().build();

        var result = supplyQueryService.handle(new GetSupplyByIdQuery(id));
        if (result.isEmpty()) return ResponseEntity.notFound().build();

        var resourceResponse = SupplyResourceFromEntityAssembler.toResourceFromEntity(result.get());
        return ResponseEntity.ok(resourceResponse);
    }
}
