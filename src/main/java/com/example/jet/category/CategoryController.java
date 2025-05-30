package com.example.jet.category;

import com.example.jet.category.dto.CategoryDTO;
import com.example.jet.category.dto.CreateCategoryDTO;
import com.example.jet.config.AuthenticatedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("new")
    CategoryDTO createCategory(@RequestBody CreateCategoryDTO body, @AuthenticationPrincipal AuthenticatedUser user) {
        return this.categoryService.createCategory(body, user.getUserId());
    }

    @PostMapping("delete")
    public ResponseEntity<Void> deleteCategory(@RequestBody String id) {
        this.categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("all")
    List<CategoryDTO> getCategories(@AuthenticationPrincipal AuthenticatedUser user) {
        return this.categoryService.getCategories(user.getUserId());
    }
}
