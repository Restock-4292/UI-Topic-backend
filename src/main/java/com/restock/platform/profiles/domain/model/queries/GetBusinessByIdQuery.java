package com.restock.platform.profiles.domain.model.queries;

/**
 * Query to get a business by ID.
 * @param businessId the unique business ID
 */
public record GetBusinessByIdQuery(int businessId) {
}
