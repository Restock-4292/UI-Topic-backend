package com.restock.platform.profiles.application.acl;

import com.restock.platform.profiles.domain.model.commands.CreateBusinessCommand;
import com.restock.platform.profiles.domain.model.commands.CreateProfileCommand;
import com.restock.platform.profiles.domain.model.commands.DeleteBusinessCommand;
import com.restock.platform.profiles.domain.model.commands.DeleteProfileCommand;
import com.restock.platform.profiles.domain.model.queries.GetProfileByEmailQuery;
import com.restock.platform.profiles.domain.model.aggregates.Profile;
import com.restock.platform.profiles.domain.model.entities.Business;
import com.restock.platform.profiles.domain.services.BusinessCommandService;
import com.restock.platform.profiles.domain.services.BusinessQueryService;
import com.restock.platform.profiles.domain.services.ProfileCommandService;
import com.restock.platform.profiles.domain.services.ProfileQueryService;
import com.restock.platform.profiles.interfaces.acl.ProfilesContextFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Profiles Context Facade Implementation
 * Coordinates domain-level operations across Profile and Business aggregates.
 */
@Service
public class ProfilesContextFacadeImpl implements ProfilesContextFacade {

    private final ProfileCommandService profileCommandService;
    private final ProfileQueryService profileQueryService;
    private final BusinessCommandService businessCommandService;
    private final BusinessQueryService businessQueryService;

    public ProfilesContextFacadeImpl(
            ProfileCommandService profileCommandService,
            ProfileQueryService profileQueryService,
            BusinessCommandService businessCommandService,
            BusinessQueryService businessQueryService
    ) {
        this.profileCommandService = profileCommandService;
        this.profileQueryService = profileQueryService;
        this.businessCommandService = businessCommandService;
        this.businessQueryService = businessQueryService;
    }

    @Override
    public void createProfile(Long userId, Long businessId) {
        // Como el comando requiere email, pero este método no lo recibe, puedes usar un valor dummy por ahora
        var command = new CreateProfileCommand(userId.intValue(), businessId.intValue(), "placeholder@email.com");
        profileCommandService.handle(command);
    }


    @Override
    public void deleteProfile(Long profileId) {
        var command = new DeleteProfileCommand(profileId.intValue());
        profileCommandService.handle(command);
    }

    @Override
    public Long fetchProfileIdByEmail(String email) {
        var query = new GetProfileByEmailQuery(email);
        Optional<Profile> profile = profileQueryService.handle(query);
        return profile.map(Profile::getId).map(Long::valueOf).orElse(0L);
    }

    @Override
    public Long createBusiness() {
        var command = new CreateBusinessCommand("Untitled", "", "", "", "");
        Optional<Business> business = businessCommandService.handle(command);
        return business.map(Business::getId).map(Long::valueOf).orElse(0L);
    }

    @Override
    public void deleteBusiness(Long businessId) {
        var command = new DeleteBusinessCommand(businessId.intValue());
        businessCommandService.handle(command);
    }
}
