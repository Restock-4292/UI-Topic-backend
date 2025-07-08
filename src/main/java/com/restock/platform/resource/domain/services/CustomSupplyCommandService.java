package com.restock.platform.resource.domain.services;

import com.restock.platform.resource.domain.model.aggregates.CustomSupply;
import com.restock.platform.resource.domain.model.commands.CreateCustomSupplyCommand;
import com.restock.platform.resource.domain.model.commands.UpdateSupplyCommand;
import com.restock.platform.resource.domain.model.commands.DeleteCustomSupplyCommand;

import java.util.Optional;

public interface CustomSupplyCommandService {
    Long handle(CreateCustomSupplyCommand command);
    Optional<CustomSupply> handle(UpdateSupplyCommand command);
    void handle(DeleteCustomSupplyCommand command);
}
