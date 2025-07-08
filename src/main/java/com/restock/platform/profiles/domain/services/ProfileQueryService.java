package com.restock.platform.profiles.domain.services;

import com.restock.platform.profiles.domain.model.aggregates.Profile;
import com.restock.platform.profiles.domain.model.queries.GetAllProfilesQuery;
import com.restock.platform.profiles.domain.model.queries.GetProfileByIdQuery;
import com.restock.platform.profiles.domain.model.queries.GetProfileByEmailQuery;

import java.util.List;
import java.util.Optional;

/**
 * Profile Query Service
 */
public interface ProfileQueryService {
    /**
     * Handle Get All Profiles Query
     *
     * @param query The query object
     * @return A list of profiles
     */
    List<Profile> handle(GetAllProfilesQuery query);

    /**
     * Handle Get Profile By Id Query
     *
     * @param query The query object
     * @return An optional profile if found
     */
    Optional<Profile> handle(GetProfileByIdQuery query);

    /**
     * Handle Get Profile By Email Query
     *
     * @param query The query object
     * @return An optional profile if found
     */
    Optional<Profile> handle(GetProfileByEmailQuery query);
}
