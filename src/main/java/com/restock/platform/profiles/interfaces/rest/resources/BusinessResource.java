package com.restock.platform.profiles.interfaces.rest.resources;

/**
 * Resource for a business.
 *
 * @param id         The business ID
 * @param name       The name of the business
 * @param email      The email of the business
 * @param phone      The phone number
 * @param address    The physical address
 * @param categories A comma-separated string of categories
 */
public record BusinessResource(
        int id,
        String name,
        String email,
        String phone,
        String address,
        String categories
) { }
