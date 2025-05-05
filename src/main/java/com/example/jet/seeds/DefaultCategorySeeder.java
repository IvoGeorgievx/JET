//package com.example.jet.seeds;
//
//import com.example.jet.category.CategoryEntity;
//import com.example.jet.category.CategoryRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.Arrays;
//import java.util.List;
//
//@Component
//public class DefaultCategorySeeder implements CommandLineRunner {
//
//    private final CategoryRepository categoryRepository;
//
//    public DefaultCategorySeeder(CategoryRepository categoryRepository) {
//        this.categoryRepository = categoryRepository;
//    }
//
//    @Override
//    public void run(String... args) {
//        List<String> defaultCategories = Arrays.asList("Food", "Transport", "Leisure", "Utilities");
//        for (String category : defaultCategories) {
//            categoryRepository.findByName(category).orElseGet(() -> {
//                CategoryEntity newCategory = new CategoryEntity();
//                newCategory.setName(category);
//                newCategory.setDefault(true);
//                newCategory.setType(category);
//                newCategory.setUser(null);
//                newCategory.setBudget(null);
//                return categoryRepository.save(newCategory);
//            });
//        }
//    }
//}


// will refactor this when needed, this works now but it runs on every app start up