package com.restock.platform.profiles.infrastructure.persistence.jpa.repositories;

import com.restock.platform.profiles.domain.model.aggregates.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

/**
 * Repository for the Profile aggregate using Spring Data JPA.
 */
@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {

    /**
     * Find a Profile by Email Address
     *
     * @param email The email as plain string
     * @return An Optional containing the Profile if found, or empty otherwise
     */
    Optional<Profile> findByEmail(String email);

    /**
     * Check if a Profile exists by Email Address
     *
     * @param email The email as plain string
     * @return True if a Profile with the given email exists
     */
    boolean existsByEmail(String email);

    /**
     * Find all Profiles with their associated Business using eager fetch
     *
     * @return A list of Profiles with Business loaded
     */
    @Query("SELECT p FROM Profile p JOIN FETCH p.business")
    List<Profile> findAllWithBusiness();

    /**
     * Find a Profile by ID with its associated Business using eager fetch
     *
     * @param id The Profile ID
     * @return An Optional containing the Profile with Business if found, or empty otherwise
     */
    @Query("SELECT p FROM Profile p JOIN FETCH p.business WHERE p.id = :id")
    Optional<Profile> findByIdWithBusiness(Integer id);
}
