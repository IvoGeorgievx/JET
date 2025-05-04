package com.example.jet.category;

import com.example.jet.user.UserEntity;
import com.example.jet.user.UserRepository;
import org.springframework.stereotype.Service;

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
//        should check here if the category name exists

        CategoryEntity newCategoryEntity = new CategoryEntity(data.getName(), data.getType(), data.getBudget(), user, Boolean.TRUE.equals(data.getDefault()));
        CategoryEntity savedCategoryEntity = categoryRepository.save(newCategoryEntity);
        return new CategoryDTO(savedCategoryEntity.getId(), savedCategoryEntity.getName(), savedCategoryEntity.getType(), savedCategoryEntity.getBudget(), savedCategoryEntity.getDefault());
    }
}
