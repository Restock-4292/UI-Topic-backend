package com.restock.platform.resource.interfaces.acl;

import com.restock.platform.resource.infrastructure.persistence.jpa.entities.ReferenceSupply;

import java.util.List;

/**
 * ReferenceSuppliesContextFacade
 * <p>
 * Facade to interact with external reference supplies (e.g., from an API or static JSON).
 * </p>
 */
public interface ReferenceSuppliesContextFacade {
    /**
     * Fetch all external reference supply names.
     */
    List<String> fetchAllReferenceSupplyNames();

    /**
     * Fetch all full reference supplies.
     */
    List<ReferenceSupply> getAllReferenceSupplies();
}
