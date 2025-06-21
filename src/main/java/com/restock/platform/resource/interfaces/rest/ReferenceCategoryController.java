package com.restock.platform.resource.interfaces.rest;

import com.restock.platform.resource.infrastructure.persistence.jpa.entities.ReferenceCategory;
import com.restock.platform.resource.interfaces.acl.ReferenceCategoriesContextFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/reference-categories", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Reference Categories", description = "Endpoints for viewing reference categories from external sources")
public class ReferenceCategoryController {

    private final ReferenceCategoriesContextFacade referenceCategoriesContextFacade;

    public ReferenceCategoryController(ReferenceCategoriesContextFacade referenceCategoriesContextFacade) {
        this.referenceCategoriesContextFacade = referenceCategoriesContextFacade;
    }

    @GetMapping
    @Operation(summary = "Get all reference categories", description = "Retrieve all available reference categories from external sources")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reference categories retrieved successfully"),
            @ApiResponse(responseCode = "204", description = "No reference categories found")
    })
    public ResponseEntity<List<ReferenceCategory>> getAllReferenceCategories() {
        var categories = referenceCategoriesContextFacade.getAllReferenceCategories();
        if (categories.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(categories);
    }
}
