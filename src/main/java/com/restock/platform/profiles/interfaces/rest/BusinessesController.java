package com.restock.platform.profiles.interfaces.rest;

import com.restock.platform.profiles.domain.model.commands.CreateBusinessCommand;
import com.restock.platform.profiles.domain.model.commands.DeleteBusinessCommand;
import com.restock.platform.profiles.domain.model.commands.UpdateBusinessCommand;
import com.restock.platform.profiles.domain.model.queries.GetAllBusinessesQuery;
import com.restock.platform.profiles.domain.model.queries.GetBusinessByIdQuery;
import com.restock.platform.profiles.domain.services.BusinessCommandService;
import com.restock.platform.profiles.domain.services.BusinessQueryService;
import com.restock.platform.profiles.interfaces.rest.resources.BusinessResource;
import com.restock.platform.profiles.interfaces.rest.resources.CreateBusinessResource;
import com.restock.platform.profiles.interfaces.rest.resources.UpdateBusinessResource;
import com.restock.platform.profiles.interfaces.rest.transform.BusinessResourceFromEntityAssembler;
import com.restock.platform.profiles.interfaces.rest.transform.CreateBusinessCommandFromResourceAssembler;
import com.restock.platform.profiles.interfaces.rest.transform.UpdateBusinessCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for Businesses.
 */
@RestController
@RequestMapping(value = "/api/v1/businesses", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Businesses", description = "Available Business Endpoints")
public class BusinessesController {

    private final BusinessCommandService commandService;
    private final BusinessQueryService queryService;

    public BusinessesController(BusinessCommandService commandService, BusinessQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    /**
     * Get a business by ID.
     * @param businessId The business ID
     * @return A {@link BusinessResource} or 404 Not Found
     */
    @GetMapping("/{businessId}")
    @Operation(summary = "Get Business by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Business found"),
            @ApiResponse(responseCode = "404", description = "Business not found")
    })
    public ResponseEntity<BusinessResource> getBusinessById(@PathVariable int businessId) {
        var result = queryService.handle(new GetBusinessByIdQuery(businessId));
        if (result.isEmpty()) return ResponseEntity.notFound().build();
        var resource = BusinessResourceFromEntityAssembler.toResourceFromEntity(result.get());
        return ResponseEntity.ok(resource);
    }

    /**
     * Get all businesses.
     * @return A list of {@link BusinessResource}
     */
    @GetMapping
    @Operation(summary = "Get all Businesses")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Businesses found"),
            @ApiResponse(responseCode = "404", description = "Businesses not found")
    })
    public ResponseEntity<List<BusinessResource>> getAllBusinesses() {
        var result = queryService.handle(new GetAllBusinessesQuery());
        if (result.isEmpty()) return ResponseEntity.notFound().build();
        var resources = result.stream()
                .map(BusinessResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    /**
     * Create a new business.
     * @param resource The {@link CreateBusinessResource}
     * @return The created {@link BusinessResource}
     */
    @PostMapping
    @Operation(summary = "Create a new Business")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Business created"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public ResponseEntity<BusinessResource> createBusiness(@RequestBody CreateBusinessResource resource) {
        CreateBusinessCommand command = CreateBusinessCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = commandService.handle(command);
        if (result.isEmpty()) return ResponseEntity.badRequest().build();
        var resourceCreated = BusinessResourceFromEntityAssembler.toResourceFromEntity(result.get());
        return new ResponseEntity<>(resourceCreated, HttpStatus.CREATED);
    }

    /**
     * Update a business.
     * @param businessId The business ID
     * @param resource The {@link UpdateBusinessResource}
     * @return 204 No Content
     */
    @PutMapping("/{businessId}")
    @Operation(summary = "Update a Business")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Business updated"),
            @ApiResponse(responseCode = "400", description = "Invalid data"),
            @ApiResponse(responseCode = "404", description = "Business not found")
    })
    public ResponseEntity<Void> updateBusiness(
            @PathVariable int businessId,
            @RequestBody UpdateBusinessResource resource) {
        UpdateBusinessCommand command = UpdateBusinessCommandFromResourceAssembler.toCommandFromResource(businessId, resource);
        commandService.handle(command);
        return ResponseEntity.noContent().build();
    }

    /**
     * Delete a business.
     * @param businessId The business ID
     * @return 204 No Content
     */
    @DeleteMapping("/{businessId}")
    @Operation(summary = "Delete a Business")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Business deleted"),
            @ApiResponse(responseCode = "404", description = "Business not found")
    })
    public ResponseEntity<Void> deleteBusiness(@PathVariable int businessId) {
        commandService.handle(new DeleteBusinessCommand(businessId));
        return ResponseEntity.noContent().build();
    }
}
