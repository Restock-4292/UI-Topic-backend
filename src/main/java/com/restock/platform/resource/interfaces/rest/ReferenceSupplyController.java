package com.restock.platform.resource.interfaces.rest;

import com.restock.platform.resource.interfaces.acl.ReferenceSuppliesContextFacade;
import com.restock.platform.resource.interfaces.rest.resources.ReferenceSupplyResource;
import com.restock.platform.resource.interfaces.rest.transform.ReferenceSupplyResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/reference-supplies", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Reference Supplies", description = "Endpoints for viewing reference supplies from external sources")
public class ReferenceSupplyController {

    private final ReferenceSuppliesContextFacade referenceSuppliesContextFacade;

    public ReferenceSupplyController(ReferenceSuppliesContextFacade referenceSuppliesContextFacade) {
        this.referenceSuppliesContextFacade = referenceSuppliesContextFacade;
    }

    @GetMapping
    @Operation(summary = "Get all reference supplies", description = "Retrieve all available reference supplies from external sources")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reference supplies retrieved successfully"),
            @ApiResponse(responseCode = "204", description = "No reference supplies found")
    })
    public ResponseEntity<List<ReferenceSupplyResource>> getAllReferenceSupplies() {
        var entities = referenceSuppliesContextFacade.getAllReferenceSupplies();

        if (entities.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        var resources = entities.stream()
                .map(ReferenceSupplyResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }
}