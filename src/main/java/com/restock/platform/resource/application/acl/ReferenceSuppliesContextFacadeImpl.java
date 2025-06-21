package com.restock.platform.resource.application.acl;

import com.restock.platform.resource.infrastructure.persistence.jpa.entities.ReferenceSupply;
import com.restock.platform.resource.infrastructure.persistence.jpa.repositories.ReferenceSupplyJpaRepository;
import com.restock.platform.resource.interfaces.acl.ReferenceSuppliesContextFacade;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ReferenceSuppliesContextFacadeImpl
 * <p>
 * Implements facade to interact with reference supplies stored in the database.
 * </p>
 */
@Service
public class ReferenceSuppliesContextFacadeImpl implements ReferenceSuppliesContextFacade {

    private final ReferenceSupplyJpaRepository referenceSupplyJpaRepository;

    public ReferenceSuppliesContextFacadeImpl(ReferenceSupplyJpaRepository referenceSupplyJpaRepository) {
        this.referenceSupplyJpaRepository = referenceSupplyJpaRepository;
    }

    @Override
    public List<String> fetchAllReferenceSupplyNames() {
        return referenceSupplyJpaRepository.findAll()
                .stream()
                .map(ReferenceSupply::getName)
                .toList();
    }

    @Override
    public List<ReferenceSupply> getAllReferenceSupplies() {
        return referenceSupplyJpaRepository.findAll();
    }
}
