package com.restock.platform.profiles.interfaces.rest.resources;

/**
 * Resource for a business category.
 *
 * @param id   The category ID
 * @param name The category name
 */
public record BusinessCategoryResource(
        Long id,
        String name
) { }
