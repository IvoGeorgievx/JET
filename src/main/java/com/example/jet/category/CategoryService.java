package com.example.jet.category;

import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public CategoryDTO createCategory(CreateCategoryDTO data) {
        CategoryEntity newCategoryEntity = new CategoryEntity(data.getName(), data.getType(), data.getBudget());
        CategoryEntity savedCategoryEntity = categoryRepository.save(newCategoryEntity);
        return new CategoryDTO(savedCategoryEntity.getId(), savedCategoryEntity.getName(), savedCategoryEntity.getType(), savedCategoryEntity.getBudget());
    }
}
