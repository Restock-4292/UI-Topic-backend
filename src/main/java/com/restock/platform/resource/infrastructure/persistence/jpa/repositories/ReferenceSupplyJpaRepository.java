package com.restock.platform.resource.infrastructure.persistence.jpa.repositories;

import com.restock.platform.resource.infrastructure.persistence.jpa.entities.ReferenceSupply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReferenceSupplyJpaRepository extends JpaRepository<ReferenceSupply, Long> {
    boolean existsByName(String name);
}
