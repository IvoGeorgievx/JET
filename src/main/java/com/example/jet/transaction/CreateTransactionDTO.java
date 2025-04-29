package com.example.jet.transaction;

import com.example.jet.category.Category;

import java.util.UUID;

public class CreateTransactionDTO {
    private TransactionType type;
    private Float amount;
    private String description;
    private Category category;
    private UUID userId;

    public CreateTransactionDTO(TransactionType type, Float amount, String description, Category category, UUID userId) {
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.category = category;
        this.userId = userId;
    }

    public CreateTransactionDTO() {
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}