package com.restock.platform.profiles.domain.services;

import com.restock.platform.profiles.domain.model.entities.BusinessCategory;
import com.restock.platform.profiles.domain.model.queries.GetAllBusinessCategoriesQuery;

import java.util.List;

/**
 * Business Category Query Service
 */
public interface BusinessCategoryQueryService {
    /**
     * Handle Get All Business Categories Query
     *
     * @param query The query object
     * @return A list of business categories
     */
    List<BusinessCategory> handle(GetAllBusinessCategoriesQuery query);
}
