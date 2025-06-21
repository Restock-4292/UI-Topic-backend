package com.restock.platform.resource.interfaces.acl;

import com.restock.platform.resource.infrastructure.persistence.jpa.entities.ReferenceCategory;

import java.util.List;

/**
 * Facade to interact with external reference categories.
 */
public interface ReferenceCategoriesContextFacade {
    List<ReferenceCategory> getAllReferenceCategories();
}
