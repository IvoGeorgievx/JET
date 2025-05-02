package com.example.jet.category;

public class CreateCategoryDTO {
    private String name;
    private CategoryType type;
    private Float budget;

    public CreateCategoryDTO() {
    }

    public CreateCategoryDTO(String name, CategoryType type, Float budget) {
        this.name = name;
        this.type = type;
        this.budget = budget;
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

    public CategoryType getType() {
        return type;
    }

    public void setType(CategoryType type) {
        this.type = type;
    }
}
