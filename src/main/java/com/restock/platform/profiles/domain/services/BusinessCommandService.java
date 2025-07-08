package com.restock.platform.profiles.domain.services;

import com.restock.platform.profiles.domain.model.commands.CreateBusinessCommand;
import com.restock.platform.profiles.domain.model.commands.UpdateBusinessCommand;
import com.restock.platform.profiles.domain.model.commands.DeleteBusinessCommand;
import com.restock.platform.profiles.domain.model.entities.Business;

import java.util.Optional;

/**
 * Business Command Service
 */
public interface BusinessCommandService {
    /**
     * Handle Create Business Command
     *
     * @param command The command to create a business
     * @return The created business
     */
    Optional<Business> handle(CreateBusinessCommand command);

    /**
     * Handle Update Business Command
     *
     * @param command The command to update the business
     */
    void handle(UpdateBusinessCommand command);

    /**
     * Handle Delete Business Command
     *
     * @param command The command to delete the business
     */
    void handle(DeleteBusinessCommand command);
}
