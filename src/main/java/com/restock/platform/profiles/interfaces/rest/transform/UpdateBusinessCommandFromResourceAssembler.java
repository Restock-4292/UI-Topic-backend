package com.restock.platform.profiles.interfaces.rest.transform;

import com.restock.platform.profiles.domain.model.commands.UpdateBusinessCommand;
import com.restock.platform.profiles.interfaces.rest.resources.UpdateBusinessResource;

/**
 * Assembler for converting UpdateBusinessResource to UpdateBusinessCommand.
 */
public class UpdateBusinessCommandFromResourceAssembler {
    public static UpdateBusinessCommand toCommandFromResource(int businessId, UpdateBusinessResource resource) {
        return new UpdateBusinessCommand(
                businessId,
                resource.name(),
                resource.email(),
                resource.phone(),
                resource.address(),
                resource.categories()
        );
    }
}
