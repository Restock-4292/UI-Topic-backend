// CreateProfileCommand.java
package com.restock.platform.profiles.domain.model.commands;

public record CreateProfileCommand(Integer userId, Integer businessId, String email) {}
