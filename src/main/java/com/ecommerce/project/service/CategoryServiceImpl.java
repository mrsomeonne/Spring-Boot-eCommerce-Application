package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

//    private List<Category> categories = new ArrayList<>();

//    private Long nextId = 1L;

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public List<Category> getAllCategories() {
//       return categories;
        return categoryRepository.findAll();
    }

    @Override
    public void createCategory(Category category) {
//        category.setCategoryId(nextId++);
//        categories.add(category);
        categoryRepository.save(category);
    }

    @Override
    public boolean deleteCategory(Long categoryId) {
//        List<Category> categories = categoryRepository.findAll();
//        Category category =
//                categories.stream().filter(c -> c.getCategoryId().equals(categoryId)).findFirst().orElse(null);

//         Throw exception directly Inline instead of Boolean true or false
//        categories.stream().filter(c -> c.getCategoryId().equals(categoryId)).findFirst()
//                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
//         Check if categoryId is equal to given categoryId

        Optional<Category> deleteCategoryOpt = categoryRepository.findById(categoryId);

        Category deleteCategory = deleteCategoryOpt.orElseThrow(null);

        if (deleteCategory == null){
            return false;
        }
//        categories.remove(category);
        categoryRepository.delete(deleteCategory);
        return true;

    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {
        Optional<Category> savedCategoryOpt = categoryRepository.findById(categoryId);

        Category savedCategory = savedCategoryOpt.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));

        category.setCategoryId(categoryId);
        savedCategory  = categoryRepository.save(category);
        return savedCategory;


//        Optional<Category> optionalCategory =
//                categories.stream().filter(c -> c.getCategoryId().equals(categoryId)).findFirst();

//        if (optionalCategory.isPresent()){
//            Category existingCategory = optionalCategory.get();
////            existingCategory.setCategoryName(category.getCategoryName());
////            return existingCategory;
//
//            Category updatedCategory = categoryRepository.save(existingCategory);
//            return updatedCategory;
//
//        }else{
//            throw new  ResponseStatusException(HttpStatus.NOT_FOUND, "Category Not Found");
//        }
    }
}
