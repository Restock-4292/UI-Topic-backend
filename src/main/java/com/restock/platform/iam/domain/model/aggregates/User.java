package com.restock.platform.iam.domain.model.aggregates;

import com.restock.platform.iam.domain.model.entities.Role;
import com.restock.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * User aggregate root
 * This class represents the aggregate root for the User entity.
 *
 * @see AuditableAbstractAggregateRoot
 */
@Entity
@Getter
@Setter
public class User extends AuditableAbstractAggregateRoot<User> {

    @NotBlank
    @Size(max = 50)
    @Column(unique = true)
    private String username;

    @NotBlank
    @Size(max = 120)
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

    public User() {
        this.role = Role.getDefaultRole();
    }

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role != null ? role : Role.getDefaultRole();
    }

    public String getRoleName() {
        return role != null ? role.getStringName() : null;
    }
}
