package com.example.jet.transaction.dto;

import com.example.jet.transaction.TransactionType;

import java.util.UUID;

public class CreateTransactionDTO {
    private TransactionType type;
    private Float amount;
    private String description;
    private UUID categoryId;
    private UUID userId;
    private Boolean recurring;


    public CreateTransactionDTO(TransactionType type, Float amount, String description, UUID categoryId, UUID userId, boolean recurring) {
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.categoryId = categoryId;
        this.userId = userId;
        this.recurring = recurring;
    }

    public CreateTransactionDTO(TransactionType type, Float amount, String description, UUID categoryId, UUID userId) {
        this(type, amount, description, categoryId, userId, false);
    }

    public CreateTransactionDTO() {
    }

    public boolean isRecurring() {
        return recurring;
    }

    public void setRecurring(boolean recurring) {
        this.recurring = recurring;
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

    public UUID getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(UUID categoryId) {
        this.categoryId = categoryId;
    }
}
