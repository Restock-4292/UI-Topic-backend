package com.restock.platform.profiles.interfaces.rest.transform;

import com.restock.platform.profiles.domain.model.entities.BusinessCategory;
import com.restock.platform.profiles.interfaces.rest.resources.BusinessCategoryResource;

/**
 * Assembler for converting a BusinessCategory entity to a resource.
 */
public class BusinessCategoryResourceFromEntityAssembler {
    public static BusinessCategoryResource toResourceFromEntity(BusinessCategory entity) {
        return new BusinessCategoryResource(
                entity.getId(),
                entity.getName()
        );
    }
}
