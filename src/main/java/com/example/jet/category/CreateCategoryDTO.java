package com.example.jet.category;

public class CreateCategoryDTO {
    private String name;
    private CategoryType type;

    public CreateCategoryDTO() {
    }

    public CreateCategoryDTO(String name, CategoryType type) {
        this.name = name;
        this.type = type;
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
