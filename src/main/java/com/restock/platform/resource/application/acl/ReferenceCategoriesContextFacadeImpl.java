package com.restock.platform.resource.application.acl;

import com.restock.platform.resource.infrastructure.persistence.jpa.entities.ReferenceCategory;
import com.restock.platform.resource.infrastructure.persistence.jpa.repositories.ReferenceCategoryJpaRepository;
import com.restock.platform.resource.interfaces.acl.ReferenceCategoriesContextFacade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReferenceCategoriesContextFacadeImpl implements ReferenceCategoriesContextFacade {

    private final ReferenceCategoryJpaRepository referenceCategoryJpaRepository;

    public ReferenceCategoriesContextFacadeImpl(ReferenceCategoryJpaRepository referenceCategoryJpaRepository) {
        this.referenceCategoryJpaRepository = referenceCategoryJpaRepository;
    }

    @Override
    public List<ReferenceCategory> getAllReferenceCategories() {
        return referenceCategoryJpaRepository.findAll();
    }
}