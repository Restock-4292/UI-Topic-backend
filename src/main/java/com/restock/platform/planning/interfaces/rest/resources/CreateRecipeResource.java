package com.restock.platform.planning.interfaces.rest.resources;


public record CreateRecipeResource(
        String name,
        String description,
        String imageUrl,
        Double totalPrice,
        int userId
) {}