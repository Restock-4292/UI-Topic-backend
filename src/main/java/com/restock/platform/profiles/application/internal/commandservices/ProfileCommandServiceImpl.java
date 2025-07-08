package com.restock.platform.profiles.application.internal.commandservices;

import com.restock.platform.iam.interfaces.acl.IamContextFacade;
import com.restock.platform.profiles.domain.model.aggregates.Profile;
import com.restock.platform.profiles.domain.model.commands.CreateProfileCommand;
import com.restock.platform.profiles.domain.model.commands.DeleteProfileCommand;
import com.restock.platform.profiles.domain.model.commands.UpdateProfileCommand;
import com.restock.platform.profiles.domain.services.ProfileCommandService;
import com.restock.platform.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;
import com.restock.platform.profiles.infrastructure.persistence.jpa.repositories.BusinessRepository;
import com.restock.platform.shared.domain.exceptions.InvalidCredentialsException;
import com.restock.platform.shared.domain.repositories.UnitOfWork;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Profile Command Service Implementation
 */
@Service
public class ProfileCommandServiceImpl implements ProfileCommandService {

    private final IamContextFacade iamContextFacade;
    private final ProfileRepository profileRepository;
    private final BusinessRepository businessRepository;
    private final UnitOfWork unitOfWork;

    /**
     * Constructor
     *
     * @param profileRepository  The Profile JPA repository
     * @param businessRepository The Business JPA repository
     * @param iamContextFacade   The IAM context facade
     * @param unitOfWork         The unit of work
     */
    public ProfileCommandServiceImpl(
            ProfileRepository profileRepository,
            BusinessRepository businessRepository,
            IamContextFacade iamContextFacade,
            UnitOfWork unitOfWork
    ) {
        this.profileRepository = profileRepository;
        this.businessRepository = businessRepository;
        this.iamContextFacade = iamContextFacade;
        this.unitOfWork = unitOfWork;
    }

    @Override
    public Optional<Profile> handle(CreateProfileCommand command) {
        // Validar existencia de User
        iamContextFacade.getUserById((long) command.userId())
                .orElseThrow(() -> new InvalidCredentialsException("User not found"));

        // Validar existencia de Business
        businessRepository.findById(command.businessId())
                .orElseThrow(() -> new InvalidCredentialsException("Business not found"));

        // Validar email único
        if (profileRepository.existsByEmail(command.email())) {
            throw new InvalidCredentialsException("Profile with this email already exists");
        }

        // Crear y guardar perfil
        var profile = new Profile(command);
        profileRepository.save(profile);
        unitOfWork.commit();
        return Optional.of(profile);
    }

    @Override
    public void handle(UpdateProfileCommand command) {
        var profile = profileRepository.findById(command.profileId())
                .orElseThrow(() -> new InvalidCredentialsException("Profile not found"));

        profile.update(
                command.firstName(),
                command.lastName(),
                command.avatar(),
                command.email(),
                command.phone(),
                command.address(),
                command.country()
        );

        profileRepository.save(profile);
        unitOfWork.commit();
    }

    @Override
    public void handle(DeleteProfileCommand command) {
        var profile = profileRepository.findById(command.profileId())
                .orElseThrow(() -> new InvalidCredentialsException("Profile not found"));

        profileRepository.delete(profile);
        unitOfWork.commit();
    }
}
