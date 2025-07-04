package com.restock.platform.resource.domain.services;

import com.restock.platform.resource.domain.model.aggregates.CustomSupply;
import com.restock.platform.resource.domain.model.queries.GetAllSuppliesQuery;
import com.restock.platform.resource.domain.model.queries.GetSupplyByIdQuery;
import com.restock.platform.resource.domain.model.queries.GetSuppliesByUserIdQuery;

import java.util.List;
import java.util.Optional;

public interface CustomSupplyQueryService {
    List<CustomSupply> handle(GetAllSuppliesQuery query);
    Optional<CustomSupply> handle(GetSupplyByIdQuery query);
    List<CustomSupply> handle(GetSuppliesByUserIdQuery query);
}
