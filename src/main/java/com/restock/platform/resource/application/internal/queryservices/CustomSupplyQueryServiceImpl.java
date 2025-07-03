package com.restock.platform.resource.application.internal.queryservices;

import com.restock.platform.resource.domain.model.aggregates.CustomSupply;
import com.restock.platform.resource.domain.model.queries.*;
import com.restock.platform.resource.domain.services.CustomSupplyQueryService;
import com.restock.platform.resource.infrastructure.persistence.jpa.repositories.CustomSupplyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomSupplyQueryServiceImpl implements CustomSupplyQueryService {

    private final CustomSupplyRepository customSupplyRepository;

    public CustomSupplyQueryServiceImpl(CustomSupplyRepository customSupplyRepository) {
        this.customSupplyRepository = customSupplyRepository;
    }

    @Override
    public List<CustomSupply> handle(GetAllSuppliesQuery query) {
        return customSupplyRepository.findAll();
    }

    @Override
    public Optional<CustomSupply> handle(GetSupplyByIdQuery query) {
        return customSupplyRepository.findById(query.supplyId());
    }

    @Override
    public List<CustomSupply> handle(GetSuppliesByUserIdQuery query) {
        return customSupplyRepository.findAllByUserId(query.userId());
    }
}
