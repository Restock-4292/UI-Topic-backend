package com.restock.platform.resource.application.internal.queryservices;

import com.restock.platform.resource.domain.model.aggregates.Supply;
import com.restock.platform.resource.domain.model.queries.*;
import com.restock.platform.resource.domain.services.SupplyQueryService;
import com.restock.platform.resource.infrastructure.persistence.jpa.repositories.SupplyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplyQueryServiceImpl implements SupplyQueryService {

    private final SupplyRepository supplyRepository;

    public SupplyQueryServiceImpl(SupplyRepository supplyRepository) {
        this.supplyRepository = supplyRepository;
    }

    @Override
    public List<Supply> handle(GetAllSuppliesQuery query) {
        return supplyRepository.findAll();
    }

    @Override
    public Optional<Supply> handle(GetSupplyByIdQuery query) {
        return supplyRepository.findById(query.supplyId());
    }

    @Override
    public List<Supply> handle(GetSuppliesByUserIdQuery query) {
        return supplyRepository.findAllByUserId(query.userId());
    }
}
