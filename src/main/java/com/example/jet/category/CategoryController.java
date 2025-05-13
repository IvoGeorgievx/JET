package com.example.jet.category;

import com.example.jet.category.dto.CategoryDTO;
import com.example.jet.category.dto.CreateCategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    List<CategoryDTO> getCategories() {
//        make sure to extract the userId from the jwt token later on.
        return this.categoryService.getCategories(UUID.fromString("7c91071f-a229-4f48-9bbf-1d0ee0e25b71"));
    }
}
