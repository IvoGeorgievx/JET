package com.example.jet.category;

import java.util.UUID;

public class CategoryDTO {
    private UUID id;
    private String name;
    private CategoryType type;

    public CategoryDTO() {
    }

    public CategoryDTO(UUID id, String name, CategoryType type) {
        this.id = id;
        this.name = name;
        this.type = type;
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


