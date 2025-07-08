package com.restock.platform.iam.interfaces.acl;

import com.restock.platform.iam.domain.model.aggregates.User;
import com.restock.platform.iam.domain.model.commands.SignUpCommand;
import com.restock.platform.iam.domain.model.entities.Role;
import com.restock.platform.iam.domain.model.queries.GetUserByIdQuery;
import com.restock.platform.iam.domain.model.queries.GetUserByUsernameQuery;
import com.restock.platform.iam.domain.services.UserCommandService;
import com.restock.platform.iam.domain.services.UserQueryService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * IamContextFacade
 * <p>
 *     This class is a facade for the IAM context. It provides a simple interface for other bounded contexts to interact with the
 *     IAM context.
 *     This class is a part of the ACL layer.
 * </p>
 *
 */
@Component
public class IamContextFacade {
    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    public IamContextFacade(UserCommandService userCommandService, UserQueryService userQueryService) {
        this.userCommandService = userCommandService;
        this.userQueryService = userQueryService;
    }

    /**
     * Creates a user with the given username and password.
     * @param username The username of the user.
     * @param password The password of the user.
     * @return The id of the created user.
     */
    public Long createUser(String username, String password) {
        var signUpCommand = new SignUpCommand(username, password, null);
        var result = userCommandService.handle(signUpCommand);
        if (result.isEmpty()) return 0L;
        return result.get().getId();
    }

    /**
     * Creates a user with the given username, password and roles.
     * @param username The username of the user.
     * @param password The password of the user.
     * @param roleId The id of role.
     * @return The id of the created user.
     */
    public Long createUser(String username, String password, Long roleId) {
        var signUpCommand = new SignUpCommand(username, password, roleId);
        var result = userCommandService.handle(signUpCommand);
        if (result.isEmpty()) return 0L;
        return result.get().getId();
    }

    /**
     * Fetches the id of the user with the given username.
     * @param username The username of the user.
     * @return The id of the user.
     */
    public Long fetchUserIdByUsername(String username) {
        var getUserByUsernameQuery = new GetUserByUsernameQuery(username);
        var result = userQueryService.handle(getUserByUsernameQuery);
        if (result.isEmpty()) return 0L;
        return result.get().getId();
    }

    /**
     * Fetches the username of the user with the given id.
     * @param userId The id of the user.
     * @return The username of the user.
     */
    public String fetchUsernameByUserId(Long userId) {
        var getUserByIdQuery = new GetUserByIdQuery(userId);
        var result = userQueryService.handle(getUserByIdQuery);
        if (result.isEmpty()) return Strings.EMPTY;
        return result.get().getUsername();
    }

    /**
     * Retrieves the User aggregate by its ID.
     * @param userId The ID of the user.
     * @return The user, if found.
     */
    public Optional<User> getUserById(Long userId) {
        var query = new GetUserByIdQuery(userId);
        return userQueryService.handle(query);
    }

}