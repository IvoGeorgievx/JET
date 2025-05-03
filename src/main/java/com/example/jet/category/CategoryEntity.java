package com.example.jet.category;

import com.example.jet.transaction.TransactionEntity;
import jakarta.persistence.*;

import java.util.Set;
import java.util.UUID;

@Entity()
@Table(name = "jet_category")
public class CategoryEntity {
    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private CategoryType type;

    @OneToMany(mappedBy = "category")
    private Set<TransactionEntity> transactionEntities;

    @Column(name = "budget", nullable = true)
    private Float budget;

    public CategoryEntity() {
    }

    public CategoryEntity(String name, CategoryType type, Float budget) {
        this.name = name;
        this.type = type;
        this.budget = budget;
    }


    public Set<TransactionEntity> getTransactions() {
        return transactionEntities;
    }

    public void setTransactions(Set<TransactionEntity> transactionEntities) {
        this.transactionEntities = transactionEntities;
    }

    public Float getBudget() {
        return budget;
    }

    public void setBudget(Float budget) {
        this.budget = budget;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryType getType() {
        return type;
    }

    public void setType(CategoryType type) {
        this.type = type;
    }
}
