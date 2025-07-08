package com.restock.platform.profiles.interfaces.rest.resources;

/**
 * Resource for creating a business.
 *
 * @param name       The business name
 * @param email      The email
 * @param phone      The phone
 * @param address    The address
 * @param categories A comma-separated string of category names
 */
public record CreateBusinessResource(
        String name,
        String email,
        String phone,
        String address,
        String categories
) { }
