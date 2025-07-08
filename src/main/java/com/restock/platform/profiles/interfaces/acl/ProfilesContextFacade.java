package com.restock.platform.profiles.interfaces.acl;

/**
 * Profiles Context Facade
 * Provides an abstraction to coordinate operations related to profiles and businesses.
 */
public interface ProfilesContextFacade {

    /**
     * Creates a new profile for the given user and business.
     * @param userId The user ID
     * @param businessId The business ID
     */
    void createProfile(Long userId, Long businessId);

    /**
     * Deletes a profile by its ID.
     * @param profileId The profile ID
     */
    void deleteProfile(Long profileId);

    /**
     * Retrieves the profile ID associated with the given email.
     * @param email The email address
     * @return The profile ID, or 0 if not found
     */
    Long fetchProfileIdByEmail(String email);

    /**
     * Creates a new business and returns its ID.
     * @return The newly created business ID, or 0 if creation failed
     */
    Long createBusiness();

    /**
     * Deletes a business by its ID.
     * @param businessId The business ID
     */
    void deleteBusiness(Long businessId);
}
