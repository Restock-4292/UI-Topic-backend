// UpdateProfileCommand.java
package com.restock.platform.profiles.domain.model.commands;

public record UpdateProfileCommand(
        Integer profileId,
        String firstName,
        String lastName,
        String avatar,
        String email,
        String phone,
        String address,
        String country
) {}
