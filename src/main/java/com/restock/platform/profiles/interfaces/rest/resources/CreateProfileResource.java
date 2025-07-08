package com.restock.platform.profiles.interfaces.rest.resources;

/**
 * Resource for creating a profile.
 *
 * @param userId     The user ID
 * @param businessId The business ID
 */
public record CreateProfileResource(Integer userId, Integer businessId, String email) {}
