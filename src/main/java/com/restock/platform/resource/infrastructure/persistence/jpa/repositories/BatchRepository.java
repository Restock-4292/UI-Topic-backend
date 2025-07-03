package com.restock.platform.resource.infrastructure.persistence.jpa.repositories;

import com.restock.platform.resource.domain.model.aggregates.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BatchRepository extends JpaRepository<Batch, Long> {
    List<Batch> findAllByCustomSupplyId(Long supplyId);
    List<Batch> findAllByUserId(Long userId);
}
