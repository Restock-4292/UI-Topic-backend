package com.restock.platform.profiles.domain.model.commands;

/**
 * Command to delete a business by ID.
 *
 * @param businessId the identifier of the business to delete
 */
public record DeleteBusinessCommand(int businessId) {}
