package com.ijse.dbms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ijse.dbms.entity.Category;
import com.ijse.dbms.repository.CategoryRepository;

@Service
public class CategoryServiceImp implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Category updateCategory(Long id, Category category) {
        Category existingCategory =categoryRepository.findById(id).orElse(category);
        if(existingCategory !=null) {
              existingCategory.setName(category.getName());
              existingCategory.setDescription(category.getDescription());
              return categoryRepository.save(existingCategory);
        }  else {
             return null;
        }

    }

    @Override
    public boolean deleteCategoryById(Long id) {
         if(categoryRepository.existsById(id)) {
             categoryRepository.deleteById(id);
             return true;
         } else {
            return false;
         }

    }

  
    
}