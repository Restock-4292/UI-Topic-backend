package com.restock.platform.profiles.application.internal.queryservices;

import com.restock.platform.profiles.domain.model.entities.Business;
import com.restock.platform.profiles.domain.model.queries.GetAllBusinessesQuery;
import com.restock.platform.profiles.domain.model.queries.GetBusinessByIdQuery;
import com.restock.platform.profiles.domain.services.BusinessQueryService;
import com.restock.platform.profiles.infrastructure.persistence.jpa.repositories.BusinessRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Business Query Service Implementation
 */
@Service
public class BusinessQueryServiceImpl implements BusinessQueryService {

    private final BusinessRepository businessRepository;

    /**
     * Constructor
     *
     * @param businessRepository Business repository
     */
    public BusinessQueryServiceImpl(BusinessRepository businessRepository) {
        this.businessRepository = businessRepository;
    }

    @Override
    public List<Business> handle(GetAllBusinessesQuery query) {
        return businessRepository.findAll();
    }

    @Override
    public Optional<Business> handle(GetBusinessByIdQuery query) {
        return businessRepository.findById(query.businessId());
    }
}
