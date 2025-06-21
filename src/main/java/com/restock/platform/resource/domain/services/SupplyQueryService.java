package com.restock.platform.resource.domain.services;

import com.restock.platform.resource.domain.model.aggregates.Supply;
import com.restock.platform.resource.domain.model.queries.GetAllSuppliesQuery;
import com.restock.platform.resource.domain.model.queries.GetSupplyByIdQuery;
import com.restock.platform.resource.domain.model.queries.GetSuppliesByUserIdQuery;

import java.util.List;
import java.util.Optional;

public interface SupplyQueryService {
    List<Supply> handle(GetAllSuppliesQuery query);
    Optional<Supply> handle(GetSupplyByIdQuery query);
    List<Supply> handle(GetSuppliesByUserIdQuery query);
}
