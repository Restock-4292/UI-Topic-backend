package com.restock.platform.profiles.interfaces.rest.transform;

import com.restock.platform.profiles.domain.model.commands.CreateProfileCommand;
import com.restock.platform.profiles.interfaces.rest.resources.CreateProfileResource;
import org.springframework.stereotype.Component;

/**
 * Assembler to convert a CreateProfileResource to a CreateProfileCommand.
 */
@Component
public class CreateProfileCommandFromResourceAssembler {
    /**
     * Converts a CreateProfileResource to a CreateProfileCommand.
     *
     * @param resource The {@link CreateProfileResource} to convert
     * @return The corresponding {@link CreateProfileCommand}
     */
    public CreateProfileCommand toCommandFromResource(CreateProfileResource resource) {
        return new CreateProfileCommand(
                resource.userId(),
                resource.businessId(),
                resource.email() // ✅ ahora pasa también el email
        );
    }
}
