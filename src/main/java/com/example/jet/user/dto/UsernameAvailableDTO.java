package com.example.jet.user.dto;

public class UsernameAvailableDTO {
    private boolean available;

    public UsernameAvailableDTO(boolean available) {
        this.available = available;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
