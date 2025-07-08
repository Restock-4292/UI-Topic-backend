package com.restock.platform.profiles.domain.model.commands;

/**
 * Command to update an existing business.
 *
 * @param businessId the business identifier
 * @param name       the new name
 * @param email      the new email
 * @param phone      the new phone
 * @param address    the new address
 * @param categories the updated business categories
 */
public record UpdateBusinessCommand(
        int businessId,
        String name,
        String email,
        String phone,
        String address,
        String categories
) {}
