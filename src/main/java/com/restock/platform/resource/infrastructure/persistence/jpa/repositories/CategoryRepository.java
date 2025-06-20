package com.restock.platform.resource.infrastructure.persistence.jpa.repositories;

import com.restock.platform.resource.domain.model.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);
    boolean existsByName(String name);
    boolean existsByNameAndIdIsNot(String name, Long id);
}
