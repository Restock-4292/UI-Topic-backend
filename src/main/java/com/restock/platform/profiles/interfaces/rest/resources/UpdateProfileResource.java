package com.restock.platform.profiles.interfaces.rest.resources;

/**
 * Resource for updating a profile.
 *
 * @param firstName First name
 * @param lastName  Last name
 * @param avatar    Avatar image URL
 * @param email     Email
 * @param phone     Phone
 * @param address   Address
 * @param country   Country
 */
public record UpdateProfileResource(
        String firstName,
        String lastName,
        String avatar,
        String email,
        String phone,
        String address,
        String country
) { }
