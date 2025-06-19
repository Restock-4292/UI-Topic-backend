package com.restock.platform.resource.infrastructure.persistence.jpa.repositories;

import com.restock.platform.resource.domain.model.aggregates.Supply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplyRepository extends JpaRepository<Supply, Long> {
    List<Supply> findAllByUserId(Long userId);
    boolean existsByNameAndUserId(String name, Long userId);
    boolean existsByNameAndUserIdAndIdIsNot(String name, Long userId, Long id);
}
