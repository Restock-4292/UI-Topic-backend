package com.restock.platform.resource.infrastructure.persistence.jpa.repositories;

import com.restock.platform.resource.domain.model.aggregates.CustomSupply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomSupplyRepository extends JpaRepository<CustomSupply, Long> {
    List<CustomSupply> findAllByUserId(Long userId);
}
