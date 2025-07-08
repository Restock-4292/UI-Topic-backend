package com.restock.platform.profiles.interfaces.rest.transform;

import com.restock.platform.profiles.domain.model.aggregates.Profile;
import com.restock.platform.profiles.interfaces.rest.resources.ProfileResource;
import com.restock.platform.profiles.interfaces.rest.resources.BusinessResource;

/**
 * Assembler to convert a Profile entity to a ProfileResource.
 */
public class ProfileResourceFromEntityAssembler {
    /**
     * Converts a Profile entity to a ProfileResource.
     *
     * @param entity The {@link Profile} entity to convert
     * @return The corresponding {@link ProfileResource}
     */
    public static ProfileResource toResourceFromEntity(Profile entity) {
        BusinessResource businessResource = (entity.getBusiness() != null)
                ? BusinessResourceFromEntityAssembler.toResourceFromEntity(entity.getBusiness())
                : null;

        return new ProfileResource(
                entity.getId(),
                entity.getFullName(),
                entity.getAvatar().getValue(), // Corregido
                entity.getEmail(),
                entity.getPhone(),
                entity.getAddress(),
                entity.getCountry(),
                entity.getUserId().getValue(), // Corregido
                entity.getBusinessId(),
                businessResource
        );
    }
}
