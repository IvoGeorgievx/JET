package com.example.jet.user;

import com.example.jet.transaction.TransactionEntity;
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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<TransactionEntity> transactionEntities;

    public UserEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserEntity() {
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


}
