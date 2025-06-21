package com.restock.platform.resource.infrastructure.persistence.jpa.repositories;

import com.restock.platform.resource.infrastructure.persistence.jpa.entities.ReferenceCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReferenceCategoryJpaRepository extends JpaRepository<ReferenceCategory, Long> {
    Optional<ReferenceCategory> findByName(String name);
}
