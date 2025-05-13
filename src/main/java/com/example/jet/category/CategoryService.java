package com.example.jet.category;

import com.example.jet.category.dto.CategoryDTO;
import com.example.jet.category.dto.CreateCategoryDTO;
import com.example.jet.user.UserEntity;
import com.example.jet.user.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;


    public CategoryService(CategoryRepository categoryRepository, UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }


    public CategoryDTO createCategory(CreateCategoryDTO data) {
        var userId = data.getUserId();
        UserEntity user = null;
        if (userId != null) {
            user = this.userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("No such user found"));
        }

        CategoryEntity existingCategory = this.categoryRepository.findByName(data.getName()
        ).orElse(null);

        if (existingCategory != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category with this name already exists.");
        }

        CategoryEntity newCategoryEntity = new CategoryEntity(data.getName(), data.getType(), data.getBudget(), user, Boolean.TRUE.equals(data.getDefault()));
        CategoryEntity savedCategoryEntity = categoryRepository.save(newCategoryEntity);
        return new CategoryDTO(savedCategoryEntity.getId(), savedCategoryEntity.getName(), savedCategoryEntity.getType(), savedCategoryEntity.getBudget(), savedCategoryEntity.getDefault());
    }

    public List<CategoryDTO> getCategories(UUID userId) {
        return this.categoryRepository.findByUser(userId).stream().map(categoryEntity -> new CategoryDTO(categoryEntity.getId(), categoryEntity.getName(), categoryEntity.getType(), categoryEntity.getBudget(), categoryEntity.getDefault())).collect(java.util.stream.Collectors.toList());
    }
}
