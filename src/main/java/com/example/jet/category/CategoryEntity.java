package com.example.jet.category;

import com.example.jet.transaction.TransactionEntity;
import com.example.jet.user.UserEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "jet_category")
public class CategoryEntity {
    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column()
    private String name;

    @Column(name = "type")
    private String type;

    @OneToMany(mappedBy = "categoryEntity", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonManagedReference("category-transactions")
    @JsonIgnoreProperties("categoryEntity")
    private Set<TransactionEntity> transactionEntities;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference("user-categories")
    private UserEntity user;

    @Column(name = "is_default")
    private Boolean isDefault = false;
    @Column(name = "budget", nullable = true)
    private Float budget;

    @Column(name = "budget_period")
    @Enumerated(EnumType.STRING)
    private CategoryBudgetPeriod budgetPeriod;


    public CategoryEntity() {
    }

    public CategoryEntity(String name, String type, Float budget, CategoryBudgetPeriod budgetPeriod, UserEntity user, Boolean isDefault) {
        this.name = name;
        this.type = type;
        this.budget = budget;
        this.budgetPeriod = budgetPeriod;
        this.user = user;
        this.isDefault = isDefault;
    }

    public CategoryEntity(String name, String type, Float budget, CategoryBudgetPeriod budgetPeriod, UserEntity user) {
        this(name, type, budget, budgetPeriod, null, false);
    }

    public Boolean getDefault() {
        return isDefault;
    }

    public void setDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }


    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public CategoryBudgetPeriod getBudgetPeriod() {
        return budgetPeriod;
    }

    public void setBudgetPeriod(CategoryBudgetPeriod budgetPeriod) {
        this.budgetPeriod = budgetPeriod;
    }

}
