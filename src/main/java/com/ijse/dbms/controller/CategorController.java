package com.ijse.dbms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ijse.dbms.entity.Category;
import com.ijse.dbms.service.CategoryService;

@RestController
@CrossOrigin(origins = "*")
public class CategorController {
  
    @Autowired
    private CategoryService categoryService;
    
    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }
    
    @PostMapping("/categories")
    public Category createCategory(@RequestBody Category category){
        return categoryService.createCategory(category);
    }

    @GetMapping("/categories/{id}")
    public Category getCategoryById(@PathVariable Long id) {
       return categoryService.getCategoryById(id);
    }
    
    @PutMapping("/categories/{id}")
    public Category updateCategory(@PathVariable Long id , @RequestBody Category category) {
        return categoryService.updateCategory(id, category);
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable Long id) {
          try {
            //status(201)
            return ResponseEntity.ok().body(categoryService.deleteCategoryById(id));
        } catch (Exception e) {
            //status(400)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }

    }
   



}