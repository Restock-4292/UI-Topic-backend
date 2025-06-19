package com.restock.platform.resource.application.internal.commandservices;

import com.restock.platform.resource.domain.model.aggregates.Batch;
import com.restock.platform.resource.domain.model.commands.CreateBatchCommand;
import com.restock.platform.resource.domain.model.commands.DeleteBatchCommand;
import com.restock.platform.resource.domain.model.commands.UpdateBatchStockCommand;
import com.restock.platform.resource.domain.model.commands.UpdateBatchExpirationDateCommand;
import com.restock.platform.resource.domain.services.BatchCommandService;
import com.restock.platform.resource.infrastructure.persistence.jpa.repositories.BatchRepository;
import com.restock.platform.resource.infrastructure.persistence.jpa.repositories.SupplyRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BatchCommandServiceImpl implements BatchCommandService {

    private final BatchRepository batchRepository;
    private final SupplyRepository supplyRepository;

    public BatchCommandServiceImpl(BatchRepository batchRepository, SupplyRepository supplyRepository) {
        this.batchRepository = batchRepository;
        this.supplyRepository = supplyRepository;
    }

    @Override
    public Long handle(CreateBatchCommand command) {
        verifySupplyExists(command.supplyId());
        var supply = supplyRepository.findById(command.supplyId())
                .orElseThrow(() -> new IllegalArgumentException("Supply not found with id: " + command.supplyId()));

        var batch = new Batch(command, supply);
        try {
            batchRepository.save(batch);
        } catch (Exception e) {
            throw new RuntimeException("Error saving batch: " + e.getMessage(), e);
        }
        return batch.getId();
    }

    @Override
    public void handle(DeleteBatchCommand command) {
        verifyBatchExists(command.batchId());
        try {
            batchRepository.deleteById(command.batchId());
        } catch (Exception e) {
            throw new RuntimeException("Error deleting batch: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<Batch> handle(UpdateBatchStockCommand command) {
        var batch = batchRepository.findById(command.batchId())
                .orElseThrow(() -> new IllegalArgumentException("Batch not found with id: " + command.batchId()));
        try {
            batch.updateStock(command.newStock());
            batchRepository.save(batch);
            return Optional.of(batch);
        } catch (Exception e) {
            throw new RuntimeException("Error updating batch stock: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<Batch> handle(UpdateBatchExpirationDateCommand command) {
        var batch = batchRepository.findById(command.batchId())
                .orElseThrow(() -> new IllegalArgumentException("Batch not found with id: " + command.batchId()));
        try {
            batch.updateExpirationDate(command.newExpirationDate());
            batchRepository.save(batch);
            return Optional.of(batch);
        } catch (Exception e) {
            throw new RuntimeException("Error updating batch expiration date: " + e.getMessage(), e);
        }
    }

    private void verifyBatchExists(Long id) {
        if (!batchRepository.existsById(id)) {
            throw new IllegalArgumentException("Batch not found with id: " + id);
        }
    }

    private void verifySupplyExists(Long id) {
        if (!supplyRepository.existsById(id)) {
            throw new IllegalArgumentException("Supply not found with id: " + id);
        }
    }
}
