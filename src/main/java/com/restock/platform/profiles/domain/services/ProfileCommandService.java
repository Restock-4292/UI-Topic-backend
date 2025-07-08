package com.restock.platform.profiles.domain.services;

import com.restock.platform.profiles.domain.model.aggregates.Profile;
import com.restock.platform.profiles.domain.model.commands.CreateProfileCommand;
import com.restock.platform.profiles.domain.model.commands.UpdateProfileCommand;
import com.restock.platform.profiles.domain.model.commands.DeleteProfileCommand;

import java.util.Optional;

/**
 * Profile Command Service
 */
public interface ProfileCommandService {
    /**
     * Handle Create Profile Command
     *
     * @param command The command to create a profile
     * @return The created profile if successful
     */
    Optional<Profile> handle(CreateProfileCommand command);

    /**
     * Handle Update Profile Command
     *
     * @param command The command to update the profile
     */
    void handle(UpdateProfileCommand command);

    /**
     * Handle Delete Profile Command
     *
     * @param command The command to delete the profile
     */
    void handle(DeleteProfileCommand command);
}
