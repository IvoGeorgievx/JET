package com.example.jet.category.dto;

import com.example.jet.category.CategoryBudgetPeriod;

import java.util.UUID;

public class CreateCategoryDTO {
    private String name;
    private String type;
    private Float budget;
    private CategoryBudgetPeriod budgetPeriod;
    private UUID userId;
    private Boolean isDefault;

    public CreateCategoryDTO() {
    }

    public CreateCategoryDTO(String name, String type, Float budget, CategoryBudgetPeriod budgetPeriod, UUID userId, Boolean isDefault) {
        this.name = name;
        this.type = type;
        this.budget = budget;
        this.userId = userId;
        this.budgetPeriod = budgetPeriod;
        this.isDefault = isDefault;
    }

    public CreateCategoryDTO(String name, String type, Float budget, CategoryBudgetPeriod budgetPeriod, UUID userId) {
        this(name, type, budget, budgetPeriod, null, false);
    }

    public Boolean getDefault() {
        return isDefault;
    }

    public void setDefault(Boolean aDefault) {
        isDefault = aDefault;
    }

    public CategoryBudgetPeriod getBudgetPeriod() {
        return budgetPeriod;
    }

    public void setBudgetPeriod(CategoryBudgetPeriod budgetPeriod) {
        this.budgetPeriod = budgetPeriod;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public Float getBudget() {
        return budget;
    }

    public void setBudget(Float budget) {
        this.budget = budget;
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
}
