package com.restock.platform.profiles.domain.model.queries;

/**
 * Query to get a profile by ID.
 * @param profileId the unique profile ID
 */
public record GetProfileByIdQuery(int profileId) {}
