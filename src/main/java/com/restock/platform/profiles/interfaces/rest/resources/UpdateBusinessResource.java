package com.restock.platform.profiles.interfaces.rest.resources;

/**
 * Resource for updating a business.
 *
 * @param name       The new name
 * @param email      The new email
 * @param phone      The new phone
 * @param address    The new address
 * @param categories New categories (comma-separated)
 */
public record UpdateBusinessResource(
        String name,
        String email,
        String phone,
        String address,
        String categories
) { }
