package com.ijse.dbms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ijse.dbms.entity.Category;

@Service
public interface CategoryService {
    List<Category> getAllCategories();
    Category createCategory(Category category);
    Category getCategoryById(Long id);
    Category updateCategory(Long id, Category category);
    boolean deleteCategoryById(Long id);
  

}