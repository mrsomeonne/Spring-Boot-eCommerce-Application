package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private List<Category> categories = new ArrayList<>();

    private Long nextId = 1L;


    @Override
    public List<Category> getAllCategories() {
        return categories;
    }

    @Override
    public void createCategory(Category category) {
        category.setCategoryId(nextId++);
        categories.add(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category category =
                categories.stream().filter(c -> c.getCategoryId().equals(categoryId)).findFirst().orElse(null);
        // Check if categoryId is equal to given categoryId
        if (category == null){
            return "Category with id {"+ category + "} Not Found";
        }
        categories.remove(category);
        return "Category with CategoryId : "+ categoryId + " deleted successfully";
    }
}
