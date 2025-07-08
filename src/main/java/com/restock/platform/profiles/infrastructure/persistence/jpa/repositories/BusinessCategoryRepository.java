package com.restock.platform.profiles.infrastructure.persistence.jpa.repositories;

import com.restock.platform.profiles.domain.model.entities.BusinessCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Business Category Repository
 */
@Repository
public interface BusinessCategoryRepository extends JpaRepository<BusinessCategory, Long> {
}