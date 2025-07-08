package com.restock.platform.profiles.interfaces.rest.transform;

import com.restock.platform.profiles.domain.model.commands.CreateBusinessCommand;
import com.restock.platform.profiles.interfaces.rest.resources.CreateBusinessResource;

/**
 * Assembler for converting CreateBusinessResource to CreateBusinessCommand.
 */
public class CreateBusinessCommandFromResourceAssembler {
    public static CreateBusinessCommand toCommandFromResource(CreateBusinessResource resource) {
        return new CreateBusinessCommand(
                resource.name(),
                resource.email(),
                resource.phone(),
                resource.address(),
                resource.categories()
        );
    }
}
