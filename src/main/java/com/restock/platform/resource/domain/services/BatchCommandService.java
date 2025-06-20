package com.restock.platform.resource.domain.services;

import com.restock.platform.resource.domain.model.aggregates.Batch;
import com.restock.platform.resource.domain.model.commands.CreateBatchCommand;
import com.restock.platform.resource.domain.model.commands.UpdateBatchStockCommand;
import com.restock.platform.resource.domain.model.commands.UpdateBatchExpirationDateCommand;
import com.restock.platform.resource.domain.model.commands.DeleteBatchCommand; // ← Asegúrate de importar esto

import java.util.Optional;

public interface BatchCommandService {
    Long handle(CreateBatchCommand command);
    Optional<Batch> handle(UpdateBatchStockCommand command);
    Optional<Batch> handle(UpdateBatchExpirationDateCommand command);
    void handle(DeleteBatchCommand command);
}
