package com.restock.platform.resource.infrastructure.persistence.jpa.repositories;

import com.restock.platform.resource.domain.model.entities.Supply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplyRepository extends JpaRepository<Supply, Long> {

    Optional<Supply> findByName(String name);
    boolean existsByName(String name);

}