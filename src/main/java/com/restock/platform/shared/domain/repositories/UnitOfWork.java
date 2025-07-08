package com.restock.platform.shared.domain.repositories;

/**
 * Contract for coordinating transactional operations
 */
public interface UnitOfWork {
    void commit();
}
