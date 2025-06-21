package com.restock.platform.resource.application.init;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restock.platform.resource.infrastructure.persistence.jpa.entities.ReferenceCategory;
import com.restock.platform.resource.infrastructure.persistence.jpa.entities.ReferenceSupply;
import com.restock.platform.resource.infrastructure.persistence.jpa.repositories.ReferenceCategoryJpaRepository;
import com.restock.platform.resource.infrastructure.persistence.jpa.repositories.ReferenceSupplyJpaRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.io.File;
import java.nio.file.Paths;

@Component
public class ReferenceSupplyInitializer {

    private final ObjectMapper objectMapper;
    private final ReferenceSupplyJpaRepository referenceSupplyJpaRepository;
    private final ReferenceCategoryJpaRepository referenceCategoryJpaRepository;

    public ReferenceSupplyInitializer(ObjectMapper objectMapper,
                                      ReferenceSupplyJpaRepository referenceSupplyJpaRepository,
                                      ReferenceCategoryJpaRepository referenceCategoryJpaRepository) {
        this.objectMapper = objectMapper;
        this.referenceSupplyJpaRepository = referenceSupplyJpaRepository;
        this.referenceCategoryJpaRepository = referenceCategoryJpaRepository;
    }

    @PostConstruct
    public void initReferenceSupplies() {
        try {
            InputStream inputStream = getClass()
                    .getClassLoader()
                    .getResourceAsStream("reference/jsondata/reference-supplies.json");

            if (inputStream == null) {
                throw new RuntimeException("reference-supplies.json not found in classpath");
            }

            List<Map<String, Object>> supplies = objectMapper.readValue(inputStream, new TypeReference<>() {});

            supplies.forEach(map -> {
                String name = (String) map.get("name");
                if (!referenceSupplyJpaRepository.existsByName(name)) {

                    String categoryName = (String) map.get("category");
                    ReferenceCategory category = referenceCategoryJpaRepository.findByName(categoryName)
                            .orElseGet(() -> referenceCategoryJpaRepository.save(new ReferenceCategory(categoryName)));

                    ReferenceSupply supply = new ReferenceSupply(
                            name,
                            (String) map.get("description"),
                            (Boolean) map.get("perishable"),
                            (String) map.get("unitName"),
                            (String) map.get("unitAbbreviation"),
                            category
                    );

                    referenceSupplyJpaRepository.save(supply);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException("Failed to load reference supplies", e);
        }
    }
}
