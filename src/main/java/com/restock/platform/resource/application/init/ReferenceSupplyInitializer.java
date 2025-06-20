package com.restock.platform.resource.application.init;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restock.platform.resource.infrastructure.persistence.jpa.entities.ReferenceSupply;
import com.restock.platform.resource.infrastructure.persistence.jpa.repositories.ReferenceSupplyJpaRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.io.File;
import java.nio.file.Paths;
@Component
public class ReferenceSupplyInitializer {

    private final ObjectMapper objectMapper;
    private final ReferenceSupplyJpaRepository referenceSupplyJpaRepository;

    public ReferenceSupplyInitializer(ObjectMapper objectMapper, ReferenceSupplyJpaRepository referenceSupplyJpaRepository) {
        this.objectMapper = objectMapper;
        this.referenceSupplyJpaRepository = referenceSupplyJpaRepository;
    }

    @PostConstruct
    public void initReferenceSupplies() {
        try {
            File file = Paths.get("src/main/java/com/restock/platform/resource/infrastructure/external/jsondata/files/reference-supplies.json").toFile();

            List<Map<String, Object>> supplies = objectMapper.readValue(file, new TypeReference<>() {});
            supplies.forEach(map -> {
                String name = (String) map.get("name");
                if (!referenceSupplyJpaRepository.existsByName(name)) {
                    ReferenceSupply supply = new ReferenceSupply(
                            name,
                            (String) map.get("description"),
                            (Boolean) map.get("perishable"),
                            (String) map.get("unitName"),
                            (String) map.get("unitAbbreviation"),
                            (String) map.get("category")
                    );
                    referenceSupplyJpaRepository.save(supply);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException("Failed to load reference supplies", e);
        }
    }
}
