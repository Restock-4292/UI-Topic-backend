package com.restock.platform.profiles.application.internal.queryservices;

import com.restock.platform.profiles.domain.model.entities.BusinessCategory;
import com.restock.platform.profiles.domain.model.queries.GetAllBusinessCategoriesQuery;
import com.restock.platform.profiles.domain.services.BusinessCategoryQueryService;
import com.restock.platform.profiles.infrastructure.persistence.jpa.repositories.BusinessCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Business Category Query Service Implementation
 */
@Service
public class BusinessCategoryQueryServiceImpl implements BusinessCategoryQueryService {

    private final BusinessCategoryRepository businessCategoryRepository;

    /**
     * Constructor
     *
     * @param businessCategoryRepository The {@link BusinessCategoryRepository} instance
     */
    public BusinessCategoryQueryServiceImpl(BusinessCategoryRepository businessCategoryRepository) {
        this.businessCategoryRepository = businessCategoryRepository;
    }

    /**
     * Handle Get All Business Categories Query
     *
     * @param query The {@link GetAllBusinessCategoriesQuery} instance
     * @return A list of {@link BusinessCategory} instances
     */
    @Override
    public List<BusinessCategory> handle(GetAllBusinessCategoriesQuery query) {
        return businessCategoryRepository.findAll();
    }
}
