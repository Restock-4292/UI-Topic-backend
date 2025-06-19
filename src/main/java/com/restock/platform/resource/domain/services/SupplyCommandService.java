package com.restock.platform.resource.domain.services;

import com.restock.platform.resource.domain.model.aggregates.Supply;
import com.restock.platform.resource.domain.model.commands.CreateSupplyCommand;
import com.restock.platform.resource.domain.model.commands.UpdateSupplyCommand;
import com.restock.platform.resource.domain.model.commands.DeleteSupplyCommand;

import java.util.Optional;

public interface SupplyCommandService {
    Long handle(CreateSupplyCommand command);
    Optional<Supply> handle(UpdateSupplyCommand command);
    void handle(DeleteSupplyCommand command);
}
