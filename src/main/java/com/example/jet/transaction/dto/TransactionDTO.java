package com.example.jet.transaction.dto;

import com.example.jet.category.CategoryEntity;
import com.example.jet.transaction.TransactionType;

import java.util.UUID;

public class TransactionDTO {
    private UUID id;
    private TransactionType type;
    private Float amount;
    private String description;
    private CategoryEntity categoryEntity;
    private UUID userId;


    public TransactionDTO() {
    }

    public TransactionDTO(UUID id, TransactionType type, Float amount, String description, CategoryEntity categoryEntity, UUID userId) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.categoryEntity = categoryEntity;
        this.userId = userId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CategoryEntity getCategory() {
        return categoryEntity;
    }

    public void setCategory(CategoryEntity categoryEntity) {
        this.categoryEntity = categoryEntity;
    }
}
