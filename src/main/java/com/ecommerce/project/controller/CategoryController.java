package com.ecommerce.project.controller;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")  //RequestMapping in Class Level
public class CategoryController {

    private CategoryService categoryService;  //If you put @Autowired it will be Field Injection

    public CategoryController(CategoryService categoryService) { //Constructor Injection
        this.categoryService = categoryService;
    }



    @GetMapping("/public/categories")
//@RequestMapping(value = "/api/public/categories" , method = RequestMethod.GET) //RequestMapping at Method Level
    public ResponseEntity<List<Category>>  getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @PostMapping("/public/categories")
//    @RequestMapping(value = "/api/public/categories" , method = RequestMethod.POST)
    public ResponseEntity<String> createCategory(@RequestBody Category category) {
        categoryService.createCategory(category);
        return new ResponseEntity<>("Category Created Successfully!", HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/categories/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId) {
        boolean deleted = categoryService.deleteCategory(categoryId);
        if (deleted) {
            return new ResponseEntity<>("Category Deleted Successfully!", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Category Not Found", HttpStatus.NOT_FOUND);
        }

        /*
            //Instead of If Else using Exceptions Using try catch

            try{
                String deleted = categoryService.deleteCategory(categoryId);
                return new ResponseEntity<>(delete , HttpStatus.OK)
                return ResponseEntity.ok(deleted);  another way of response status
            } catch (ResponseEntityException e) {
                return new ResponseEntity(e.getReason(), e.getStatusCode());
            }
         */
    }

    @PutMapping("/public/categories/{categoryId}")
    public ResponseEntity<String> updateCategory(@RequestBody Category category, @PathVariable Long categoryId){
        try{
           Category editCategory = categoryService.updateCategory(category, categoryId);
           return new ResponseEntity<>("Category Id with category id:"+ categoryId, HttpStatus.OK);
        }catch (ResponseStatusException e){
            return new ResponseEntity<>("Category Not Found", HttpStatus.NOT_FOUND);
        }
    }
}
