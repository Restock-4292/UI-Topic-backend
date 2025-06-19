package com.restock.platform.resource.application.internal.commandservices;

import com.restock.platform.resource.domain.model.aggregates.Supply;
import com.restock.platform.resource.domain.model.commands.CreateSupplyCommand;
import com.restock.platform.resource.domain.model.commands.DeleteSupplyCommand;
import com.restock.platform.resource.domain.model.commands.UpdateSupplyCommand;
import com.restock.platform.resource.domain.model.entities.Category;
import com.restock.platform.resource.domain.model.valueobjects.UnitMeasurement;
import com.restock.platform.resource.domain.services.SupplyCommandService;
import com.restock.platform.resource.infrastructure.persistence.jpa.repositories.SupplyRepository;
import com.restock.platform.resource.infrastructure.persistence.jpa.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SupplyCommandServiceImpl implements SupplyCommandService {

    private final SupplyRepository supplyRepository;
    private final CategoryRepository categoryRepository;

    public SupplyCommandServiceImpl(SupplyRepository supplyRepository, CategoryRepository categoryRepository) {
        this.supplyRepository = supplyRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Long handle(CreateSupplyCommand command) {
        Category category = categoryRepository.findById(command.categoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found with id: " + command.categoryId()));

        var supply = createSupplyFromCommand(command, category);

        try {
            supplyRepository.save(supply);
        } catch (Exception e) {
            throw new RuntimeException("Error saving supply: " + e.getMessage(), e);
        }

        return supply.getId();
    }

    @Override
    public Optional<Supply> handle(UpdateSupplyCommand command) {
        var supply = supplyRepository.findById(command.supplyId())
                .orElseThrow(() -> new IllegalArgumentException("Supply not found with id: " + command.supplyId()));

        Category category = categoryRepository.findById(command.categoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found with id: " + command.categoryId()));

        try {
            var updatedSupply = updateSupplyFromCommand(supply, command, category);
            supplyRepository.save(updatedSupply);
            return Optional.of(updatedSupply);
        } catch (Exception e) {
            throw new RuntimeException("Error updating supply: " + e.getMessage(), e);
        }
    }

    @Override
    public void handle(DeleteSupplyCommand command) {
        if (!supplyRepository.existsById(command.supplyId())) {
            throw new IllegalArgumentException("Supply not found with id: " + command.supplyId());
        }

        try {
            supplyRepository.deleteById(command.supplyId());
        } catch (Exception e) {
            throw new RuntimeException("Error deleting supply: " + e.getMessage(), e);
        }
    }

    private Supply createSupplyFromCommand(CreateSupplyCommand command, Category category) {
        var unitMeasurement = new UnitMeasurement(
                command.unitMeasurementName(),
                command.unitMeasurementAbbreviation()
        );

        return new Supply(
                command.name(),
                command.description(),
                command.perishable(),
                command.minStock(),
                command.maxStock(),
                command.price(),
                command.userId(),
                unitMeasurement,
                category
        );
    }

    private Supply updateSupplyFromCommand(Supply supply, UpdateSupplyCommand command, Category category) {
        var unitMeasurement = new UnitMeasurement(
                command.unitMeasurementName(),
                command.unitMeasurementAbbreviation()
        );

        return supply.update(
                command.name(),
                command.description(),
                command.perishable(),
                command.minStock(),
                command.maxStock(),
                command.price(),
                unitMeasurement,
                category
        );
    }
}
