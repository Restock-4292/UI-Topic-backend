package com.restock.platform.profiles.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

/**
 * Value Object that represents an Avatar URL.
 */
@Embeddable
public class Avatar {

    private static final String DEFAULT_AVATAR_URL = "https://t4.ftcdn.net/jpg/02/15/84/43/360_F_215844325_ttX9YiIIyeaR7Ne6EaLLjMAmy4GvPC69.jpg";

    @Column(name = "avatar")
    private String value;

    public Avatar() {
        this.value = DEFAULT_AVATAR_URL;
    }

    public Avatar(String value) {
        String url = (value == null || value.trim().isEmpty()) ? DEFAULT_AVATAR_URL : value;

        if (!isValidUrl(url)) {
            throw new IllegalArgumentException("Invalid URL format for avatar.");
        }

        this.value = url;
    }

    private boolean isValidUrl(String url) {
        try {
            new URL(url);
            return true;
        } catch (MalformedURLException ex) {
            return false;
        }
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Avatar)) return false;
        Avatar avatar = (Avatar) o;
        return Objects.equals(value, avatar.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
