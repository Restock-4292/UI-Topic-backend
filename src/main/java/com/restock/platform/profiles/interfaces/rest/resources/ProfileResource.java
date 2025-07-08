package com.restock.platform.profiles.interfaces.rest.resources;

/**
 * Resource for a profile with business details.
 *
 * @param id         The profile ID
 * @param fullName   The full name
 * @param avatar     The avatar URL
 * @param email      The email address
 * @param phone      The phone
 * @param address    The address
 * @param country    The country
 * @param userId     The associated user ID
 * @param businessId The associated business ID
 * @param business   The business resource
 */
public record ProfileResource(
        int id,
        String fullName,
        String avatar,
        String email,
        String phone,
        String address,
        String country,
        int userId,
        int businessId,
        BusinessResource business
) { }
