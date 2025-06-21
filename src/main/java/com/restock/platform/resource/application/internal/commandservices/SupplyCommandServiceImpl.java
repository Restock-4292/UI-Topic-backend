package com.restock.platform.resource.application.internal.commandservices;

import com.restock.platform.resource.domain.model.aggregates.Supply;
import com.restock.platform.resource.domain.model.commands.CreateSupplyCommand;
import com.restock.platform.resource.domain.model.commands.DeleteSupplyCommand;
import com.restock.platform.resource.domain.model.commands.UpdateSupplyCommand;
import com.restock.platform.resource.domain.services.SupplyCommandService;
import com.restock.platform.resource.infrastructure.persistence.jpa.repositories.SupplyRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SupplyCommandServiceImpl implements SupplyCommandService {

    private final SupplyRepository supplyRepository;

    public SupplyCommandServiceImpl(SupplyRepository supplyRepository) {
        this.supplyRepository = supplyRepository;
    }

    @Override
    public Long handle(CreateSupplyCommand command) {
        var supply = createSupplyFromCommand(command);

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

        try {
            var updatedSupply = supply.update(command.stockRange(), command.price(), command.description());
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

    private Supply createSupplyFromCommand(CreateSupplyCommand command) {
        return new Supply(
                command.referenceSupplyId(),
                command.stockRange(),
                command.price(),
                command.description(),
                command.userId()
        );
    }
}
