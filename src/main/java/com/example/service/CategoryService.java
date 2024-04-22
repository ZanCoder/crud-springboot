package com.example.service;

import com.example.Entity.Category;
import com.example.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAllCategories();
    CategoryDto getCategoryById(Long id);
    CategoryDto save(CategoryDto categoryDto);
    CategoryDto updateCategory(CategoryDto categoryDto);
    void deleteCategoryById(Long id);
    List<Category> findByName(String name);
}
