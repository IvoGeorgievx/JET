package com.example.jet.user;

import jakarta.persistence.*;

import java.util.UUID;

@Entity()
@Table(name = "jet_user")
public class User {

    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(unique = true, name = "username")
    private String username;
    @Column()
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
