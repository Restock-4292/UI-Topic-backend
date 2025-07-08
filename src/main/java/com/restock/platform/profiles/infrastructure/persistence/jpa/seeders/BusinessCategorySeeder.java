package com.restock.platform.profiles.infrastructure.persistence.jpa.seeders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restock.platform.profiles.domain.model.entities.BusinessCategory;
import com.restock.platform.profiles.infrastructure.persistence.jpa.repositories.BusinessCategoryRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

/**
 * Seeds initial business categories from a JSON file if the database is empty.
 */
@Component
public class BusinessCategorySeeder {

    private final BusinessCategoryRepository repository;

    @Value("classpath:jsonData/businessCategories.json")
    private Resource jsonFile;

    /**
     * Constructor for dependency injection
     * @param repository BusinessCategory JPA repository
     */
    public BusinessCategorySeeder(BusinessCategoryRepository repository) {
        this.repository = repository;
    }

    /**
     * Initializes seeding on application startup
     * @throws Exception if file reading or parsing fails
     */
    @PostConstruct
    public void seed() throws Exception {
        if (repository.count() > 0) return;

        ObjectMapper mapper = new ObjectMapper();

        try (InputStream inputStream = jsonFile.getInputStream()) {
            List<BusinessCategory> categories = mapper.readValue(inputStream, new TypeReference<>() {});
            repository.saveAll(categories);
        }

        try {
            if (repository.count() == 0) {
                var categories = List.of(
                        new BusinessCategory("Retail"),
                        new BusinessCategory("Wholesale")
                );
                repository.saveAll(categories);
            }
        } catch (Exception e) {
            e.printStackTrace(); // <-- esto mostrará el error real
            throw e; // para que siga fallando, pero con información completa
        }
    }


}
