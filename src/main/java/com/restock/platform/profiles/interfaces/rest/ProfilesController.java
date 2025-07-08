package com.restock.platform.profiles.interfaces.rest;

import com.restock.platform.profiles.domain.model.commands.CreateProfileCommand;
import com.restock.platform.profiles.domain.model.commands.DeleteProfileCommand;
import com.restock.platform.profiles.domain.model.commands.UpdateProfileCommand;
import com.restock.platform.profiles.domain.model.queries.GetAllProfilesQuery;
import com.restock.platform.profiles.domain.model.queries.GetProfileByIdQuery;
import com.restock.platform.profiles.domain.services.ProfileCommandService;
import com.restock.platform.profiles.domain.services.ProfileQueryService;
import com.restock.platform.profiles.interfaces.rest.resources.CreateProfileResource;
import com.restock.platform.profiles.interfaces.rest.resources.ProfileResource;
import com.restock.platform.profiles.interfaces.rest.resources.UpdateProfileResource;
import com.restock.platform.profiles.interfaces.rest.transform.CreateProfileCommandFromResourceAssembler;
import com.restock.platform.profiles.interfaces.rest.transform.ProfileResourceFromEntityAssembler;
import com.restock.platform.profiles.interfaces.rest.transform.UpdateProfileCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for handling Profile endpoints.
 */
@RestController
@RequestMapping(value = "/api/v1/profiles", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Profiles", description = "Available Profile Endpoints")
public class ProfilesController {

    private final ProfileCommandService commandService;
    private final ProfileQueryService queryService;
    private final CreateProfileCommandFromResourceAssembler commandAssembler;

    public ProfilesController(ProfileCommandService commandService, ProfileQueryService queryService, CreateProfileCommandFromResourceAssembler commandAssembler) {
        this.commandService = commandService;
        this.queryService = queryService;
        this.commandAssembler = commandAssembler;
    }

    /**
     * Create a new profile.
     * @param resource The {@link CreateProfileResource} resource
     * @return A {@link ProfileResource} or 400 Bad Request
     */
    @PostMapping
    @Operation(summary = "Create a new profile")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Profile created"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public ResponseEntity<ProfileResource> createProfile(@RequestBody CreateProfileResource resource) {
        CreateProfileCommand command = commandAssembler.toCommandFromResource(resource);
        var result = commandService.handle(command);
        if (result.isEmpty()) return ResponseEntity.badRequest().build();
        var profile = result.get();
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile);
        return new ResponseEntity<>(profileResource, HttpStatus.CREATED);
    }

    /**
     * Get a profile by ID.
     * @param profileId The profile's ID
     * @return A {@link ProfileResource} or 404 Not Found
     */
    @GetMapping("/{profileId}")
    @Operation(summary = "Get profile by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Profile found"),
            @ApiResponse(responseCode = "404", description = "Profile not found")
    })
    public ResponseEntity<ProfileResource> getProfileById(@PathVariable int profileId) {
        var query = new GetProfileByIdQuery(profileId);
        var result = queryService.handle(query);
        if (result.isEmpty()) return ResponseEntity.notFound().build();
        var profile = result.get();
        var resource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile);
        return ResponseEntity.ok(resource);
    }

    /**
     * Get all profiles.
     * @return A list of {@link ProfileResource} or 404 Not Found
     */
    @GetMapping
    @Operation(summary = "Get all profiles")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Profiles found"),
            @ApiResponse(responseCode = "404", description = "Profiles not found")
    })
    public ResponseEntity<List<ProfileResource>> getAllProfiles() {
        var result = queryService.handle(new GetAllProfilesQuery());
        if (result.isEmpty()) return ResponseEntity.notFound().build();
        var resources = result.stream()
                .map(ProfileResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    /**
     * Update a profile.
     * @param profileId The profile ID
     * @param resource The {@link UpdateProfileResource}
     * @return 204 No Content or 400 Bad Request
     */
    @PutMapping("/{profileId}")
    @Operation(summary = "Update a profile")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Profile updated"),
            @ApiResponse(responseCode = "400", description = "Invalid data"),
            @ApiResponse(responseCode = "404", description = "Profile not found")
    })
    public ResponseEntity<Void> updateProfile(
            @PathVariable int profileId,
            @RequestBody UpdateProfileResource resource) {
        UpdateProfileCommand command = UpdateProfileCommandFromResourceAssembler.toCommandFromResource(profileId, resource);
        commandService.handle(command);
        return ResponseEntity.noContent().build();
    }

    /**
     * Delete a profile.
     * @param profileId The profile ID
     * @return 204 No Content
     */
    @DeleteMapping("/{profileId}")
    @Operation(summary = "Delete a profile")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Profile deleted"),
            @ApiResponse(responseCode = "404", description = "Profile not found")
    })
    public ResponseEntity<Void> deleteProfile(@PathVariable int profileId) {
        commandService.handle(new DeleteProfileCommand(profileId));
        return ResponseEntity.noContent().build();
    }
}
