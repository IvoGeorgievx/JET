package com.example.jet.config;

import java.util.UUID;

public class AuthenticatedUser {
    private final UUID userId;
    private final String username;

    public AuthenticatedUser(UUID userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }
}

