package com.restock.platform.resource.interfaces.rest;

import com.restock.platform.resource.domain.model.queries.*;
import com.restock.platform.resource.domain.model.commands.CreateSupplyCommand;
import com.restock.platform.resource.domain.services.SupplyCommandService;
import com.restock.platform.resource.domain.services.SupplyQueryService;
import com.restock.platform.resource.interfaces.rest.resources.CreateSupplyResource;
import com.restock.platform.resource.interfaces.rest.resources.SupplyResource;
import com.restock.platform.resource.interfaces.rest.transform.CreateSupplyCommandFromResourceAssembler;
import com.restock.platform.resource.interfaces.rest.transform.SupplyResourceFromEntityAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/supplies")
public class SupplyController {

    private final SupplyCommandService supplyCommandService;
    private final SupplyQueryService supplyQueryService;

    public SupplyController(SupplyCommandService supplyCommandService, SupplyQueryService supplyQueryService) {
        this.supplyCommandService = supplyCommandService;
        this.supplyQueryService = supplyQueryService;
    }

//    @PostMapping
//    public ResponseEntity<SupplyResource> createSupply(@RequestBody CreateSupplyResource resource) {
//        CreateSupplyCommand command = CreateSupplyCommandFromResourceAssembler.toCommandFromResource(resource);
//        var supply = supplyCommandService.handle(command);
//        return ResponseEntity.ok(SupplyResourceFromEntityAssembler.toResourceFromEntity(supply));
//    }

    @GetMapping
    public ResponseEntity<List<SupplyResource>> getAllSupplies() {
        var supplies = supplyQueryService.handle(new GetAllSuppliesQuery());
        var resources = supplies.stream().map(SupplyResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplyResource> getSupplyById(@PathVariable Long id) {
        return supplyQueryService.handle(new GetSupplyByIdQuery(id))
                .map(SupplyResourceFromEntityAssembler::toResourceFromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SupplyResource>> getSuppliesByUserId(@PathVariable Long userId) {
        var supplies = supplyQueryService.handle(new GetSuppliesByUserIdQuery(userId));
        var resources = supplies.stream().map(SupplyResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(resources);
    }
}
