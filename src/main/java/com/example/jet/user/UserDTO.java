package com.example.jet.user;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class UserDTO {
    private UUID id;
    private String username;
    private Boolean isFirstLogin;


    public UserDTO(UUID id, String username, Boolean isFirstLogin) {
        this.id = id;
        this.username = username;
        this.isFirstLogin = isFirstLogin;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonProperty("isFirstLogin")
    public Boolean isFirstLogin() {
        return isFirstLogin;
    }

    public void setFirstLogin(Boolean firstLogin) {
        isFirstLogin = firstLogin;
    }
}
