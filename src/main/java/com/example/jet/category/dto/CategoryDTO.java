package com.example.jet.category.dto;

import com.example.jet.category.CategoryBudgetPeriod;

import java.util.UUID;

public class CategoryDTO {
    private UUID id;
    private String name;
    private String type;
    private Float budget;
    private CategoryBudgetPeriod budgetPeriod;
    private Boolean isDefault;

    public CategoryDTO(UUID id, String name, String type, Boolean aDefault) {
    }


    public CategoryDTO(UUID id, String name, String type, Float budget, CategoryBudgetPeriod budgetPeriod, Boolean isDefault) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.budget = budget;
        this.budgetPeriod = budgetPeriod;
        this.isDefault = isDefault;
    }

    public Boolean getDefault() {
        return isDefault;
    }

    public void setDefault(Boolean aDefault) {
        isDefault = aDefault;
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


