package com.restock.platform.profiles.interfaces.rest.transform;

import com.restock.platform.profiles.domain.model.entities.Business;
import com.restock.platform.profiles.interfaces.rest.resources.BusinessResource;

/**
 * Assembler for converting a Business entity to a resource.
 */
public class BusinessResourceFromEntityAssembler {
    public static BusinessResource toResourceFromEntity(Business entity) {
        return new BusinessResource(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getAddress(),
                entity.getCategories()
        );
    }
}
