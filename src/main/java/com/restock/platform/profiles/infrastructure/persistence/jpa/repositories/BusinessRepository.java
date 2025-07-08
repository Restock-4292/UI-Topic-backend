package com.restock.platform.profiles.infrastructure.persistence.jpa.repositories;

import com.restock.platform.profiles.domain.model.entities.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Business Repository
 */
@Repository
public interface BusinessRepository extends JpaRepository<Business, Integer> {
}