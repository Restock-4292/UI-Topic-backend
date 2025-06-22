package com.restock.platform.planning.interfaces.rest;

import com.restock.platform.planning.application.internal.commandservices.RecipeCommandServiceImpl;
import com.restock.platform.planning.application.internal.queryservices.RecipeQueryServiceImpl;
import com.restock.platform.planning.domain.model.queries.GetRecipeByIdQuery;
import com.restock.platform.planning.interfaces.rest.resources.CreateRecipeResource;
import com.restock.platform.planning.interfaces.rest.resources.RecipeResource;
import com.restock.platform.planning.interfaces.rest.transform.CreateRecipeCommandFromResourceAssembler;
import com.restock.platform.planning.interfaces.rest.transform.RecipeResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/recipes", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Recipes", description = "Recipe management endpoints")
public class RecipesController {

    private final RecipeCommandServiceImpl recipeCommandService;
    private final RecipeQueryServiceImpl recipeQueryService;

    public RecipesController(RecipeCommandServiceImpl recipeCommandService,
                             RecipeQueryServiceImpl recipeQueryService) {
        this.recipeCommandService = recipeCommandService;
        this.recipeQueryService = recipeQueryService;
    }

    @PostMapping
    @Operation(summary = "Create a new recipe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Recipe created"),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    public ResponseEntity<RecipeResource> createRecipe(@RequestBody CreateRecipeResource resource) {
        var createCommand = CreateRecipeCommandFromResourceAssembler.toCommandFromResource(resource);
        var recipeId = recipeCommandService.handle(createCommand);

        if (recipeId == null || recipeId <= 0) {
            return ResponseEntity.badRequest().build();
        }

        var query = new GetRecipeByIdQuery(recipeId);
        var recipeOptional = recipeQueryService.handle(query);

        if (recipeOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var recipeResource = RecipeResourceFromEntityAssembler.toResourceFromEntity(recipeOptional.get());
        return new ResponseEntity<>(recipeResource, HttpStatus.CREATED);
    }
}