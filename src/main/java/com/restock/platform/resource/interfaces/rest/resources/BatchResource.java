package com.restock.platform.resource.interfaces.rest.resources;

import java.time.LocalDate;

public record BatchResource(
        Long id,
        Long userId,
        Long supplyId,
        double stock,
        LocalDate expirationDate
) {
}
