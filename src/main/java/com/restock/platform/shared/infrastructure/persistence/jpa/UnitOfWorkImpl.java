package com.restock.platform.shared.infrastructure.persistence.jpa;

import com.restock.platform.shared.domain.repositories.UnitOfWork;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

/**
 * JPA-based implementation of UnitOfWork
 */
@Component
public class UnitOfWorkImpl implements UnitOfWork {

    private final EntityManager entityManager;

    public UnitOfWorkImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void commit() {
        entityManager.flush();
    }
}
