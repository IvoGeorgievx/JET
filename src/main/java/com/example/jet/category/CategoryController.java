package com.example.jet.category;

import com.example.jet.category.dto.CategoryDTO;
import com.example.jet.category.dto.CreateCategoryDTO;
import com.example.jet.config.AuthenticatedUser;
import org.springframework.beans.factory.annotation.Autowired;
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
    CategoryDTO createCategory(@RequestBody CreateCategoryDTO body) {
        return this.categoryService.createCategory(body);
    }

    @GetMapping("all")
    List<CategoryDTO> getCategories(@AuthenticationPrincipal AuthenticatedUser user) {
        return this.categoryService.getCategories(user.getUserId());
    }
}
