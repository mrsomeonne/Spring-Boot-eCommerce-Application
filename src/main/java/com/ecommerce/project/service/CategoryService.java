package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategories();

    void createCategory(Category category);

    boolean deleteCategory(Long categoryId);

    Category updateCategory(Category category, Long categoryId);
}
