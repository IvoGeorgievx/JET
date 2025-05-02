package com.example.jet.category;

import com.example.jet.transaction.Transaction;
import jakarta.persistence.*;

import java.util.Set;
import java.util.UUID;

@Entity()
@Table(name = "jet_category")
public class Category {
    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private CategoryType type;

    @OneToMany(mappedBy = "category")
    private Set<Transaction> transactions;

    @Column(name = "budge", nullable = true)
    private Float budget;

    public Category() {
    }

    public Category(String name, CategoryType type, Float budget) {
        this.name = name;
        this.type = type;
        this.budget = budget;
    }


    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
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
