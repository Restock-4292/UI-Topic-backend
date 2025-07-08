package com.restock.platform.profiles.interfaces.rest.transform;

import com.restock.platform.profiles.domain.model.commands.UpdateProfileCommand;
import com.restock.platform.profiles.interfaces.rest.resources.UpdateProfileResource;

/**
 * Assembler for converting UpdateProfileResource to UpdateProfileCommand.
 */
public class UpdateProfileCommandFromResourceAssembler {
    public static UpdateProfileCommand toCommandFromResource(int profileId, UpdateProfileResource resource) {
        return new UpdateProfileCommand(
                profileId,
                resource.firstName(),
                resource.lastName(),
                resource.avatar(),
                resource.email(),
                resource.phone(),
                resource.address(),
                resource.country()
        );
    }
}
