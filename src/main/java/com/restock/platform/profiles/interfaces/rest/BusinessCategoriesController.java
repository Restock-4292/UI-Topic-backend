package com.restock.platform.profiles.interfaces.rest;

import com.restock.platform.profiles.domain.model.queries.GetAllBusinessCategoriesQuery;
import com.restock.platform.profiles.domain.services.BusinessCategoryQueryService;
import com.restock.platform.profiles.interfaces.rest.resources.BusinessCategoryResource;
import com.restock.platform.profiles.interfaces.rest.transform.BusinessCategoryResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for Business Categories.
 */
@RestController
@RequestMapping(value = "/api/v1/business-categories", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Business Categories", description = "Available Business Categories Endpoints")
public class BusinessCategoriesController {

    private final BusinessCategoryQueryService queryService;

    public BusinessCategoriesController(BusinessCategoryQueryService queryService) {
        this.queryService = queryService;
    }

    /**
     * Get all business categories.
     * @return A list of {@link BusinessCategoryResource} or 404 Not Found
     */
    @GetMapping
    @Operation(summary = "Get all Business Categories")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Business Categories found"),
            @ApiResponse(responseCode = "404", description = "Business Categories not found")
    })
    public ResponseEntity<List<BusinessCategoryResource>> getAllBusinessCategories() {
        var categories = queryService.handle(new GetAllBusinessCategoriesQuery());
        if (categories.isEmpty()) return ResponseEntity.notFound().build();
        var resources = categories.stream()
                .map(BusinessCategoryResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }
}
