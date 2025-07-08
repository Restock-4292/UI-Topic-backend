package com.restock.platform.profiles.domain.model.commands;

/**
 * Command to trigger the creation of a business.
 * Includes required fields: name, email, phone, address, categories.
 */
public record CreateBusinessCommand(
        String name,
        String email,
        String phone,
        String address,
        String categories
) {}
