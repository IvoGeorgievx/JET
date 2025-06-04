package com.example.jet.user;

import com.example.jet.category.CategoryEntity;
import com.example.jet.transaction.TransactionEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity()
@Table(name = "jet_user")
public class UserEntity {

    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(unique = true, name = "username")
    private String username;
    @Column()
    private String password;

    @Column()
    private Boolean isFirstLogin;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference("user-categories")
    @JsonIgnoreProperties("user")
    private List<CategoryEntity> categories;

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    @JsonManagedReference("user-transactions")
    @JsonIgnoreProperties("userEntity")
    private List<TransactionEntity> transactionEntities;

    public UserEntity(String username, String password, Boolean isFirstLogin) {
        this.username = username;
        this.password = password;
        this.isFirstLogin = isFirstLogin;
    }

    public UserEntity() {
    }

    public List<CategoryEntity> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryEntity> categories) {
        this.categories = categories;
    }

    public List<TransactionEntity> getTransactions() {
        return transactionEntities;
    }

    public void setTransactions(List<TransactionEntity> transactionEntities) {
        this.transactionEntities = transactionEntities;
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

    public Boolean getFirstLogin() {
        return isFirstLogin;
    }

    public void setFirstLogin(Boolean firstLogin) {
        isFirstLogin = firstLogin;
    }


}
