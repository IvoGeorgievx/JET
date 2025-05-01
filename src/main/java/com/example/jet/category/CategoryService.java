package com.example.jet.category;

import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public CategoryDTO createCategory(CreateCategoryDTO data) {
        Category newCategory = new Category(data.getName(), data.getType());
        Category savedCategory = categoryRepository.save(newCategory);
        return new CategoryDTO(savedCategory.getId(), savedCategory.getName(), savedCategory.getType());
    }
}
