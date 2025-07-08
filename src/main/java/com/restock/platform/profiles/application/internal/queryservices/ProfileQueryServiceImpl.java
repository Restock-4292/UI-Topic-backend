package com.restock.platform.profiles.application.internal.queryservices;

import com.restock.platform.profiles.domain.model.aggregates.Profile;
import com.restock.platform.profiles.domain.model.queries.GetAllProfilesQuery;
import com.restock.platform.profiles.domain.model.queries.GetProfileByEmailQuery;
import com.restock.platform.profiles.domain.model.queries.GetProfileByIdQuery;
import com.restock.platform.profiles.domain.services.ProfileQueryService;
import com.restock.platform.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Profile Query Service Implementation
 */
@Service
public class ProfileQueryServiceImpl implements ProfileQueryService {

    private final ProfileRepository profileRepository;

    /**
     * Constructor
     *
     * @param profileRepository Profile repository
     */
    public ProfileQueryServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    // inherited javadoc
    @Override
    public Optional<Profile> handle(GetProfileByIdQuery query) {
        return profileRepository.findByIdWithBusiness(query.profileId());
    }

    // inherited javadoc
    @Override
    public Optional<Profile> handle(GetProfileByEmailQuery query) {
        return profileRepository.findByEmail(query.email());
    }

    // inherited javadoc
    @Override
    public List<Profile> handle(GetAllProfilesQuery query) {
        return profileRepository.findAllWithBusiness();
    }
}
