package com.example.service.impl;

import com.example.Entity.Category;
import com.example.dto.CategoryDto;
import com.example.dto.ProductDto;
import com.example.repository.CategoryRepository;
import com.example.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryDto mapToDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        return categoryDto;
    }

    public Category mapToEntity(CategoryDto categoryDto) {
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());
        return category;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDto> categoryDtos = categories.stream().map(category -> mapToDto(category)).collect(Collectors.toList());
        return categoryDtos;
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        Category category = categoryRepository.findById(id).orElse(null);
        return mapToDto(category);
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        return mapToDto(categoryRepository.save(mapToEntity(categoryDto)));
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto) {
        return mapToDto(categoryRepository.saveAndFlush(mapToEntity(categoryDto)));
    }

    @Override
    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<Category> findByName(String name) {
        return categoryRepository.findByName(name);
    }
}
