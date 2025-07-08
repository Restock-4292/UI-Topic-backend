package com.restock.platform.profiles.domain.services;

import com.restock.platform.profiles.domain.model.entities.Business;
import com.restock.platform.profiles.domain.model.queries.GetAllBusinessesQuery;
import com.restock.platform.profiles.domain.model.queries.GetBusinessByIdQuery;

import java.util.List;
import java.util.Optional;

/**
 * Business Query Service
 */
public interface BusinessQueryService {
    /**
     * Handle Get All Businesses Query
     *
     * @param query The query object
     * @return A list of businesses
     */
    List<Business> handle(GetAllBusinessesQuery query);

    /**
     * Handle Get Business By Id Query
     *
     * @param query The query object
     * @return An optional business if found
     */
    Optional<Business> handle(GetBusinessByIdQuery query);
}
