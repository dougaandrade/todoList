package com.ignis.to_do.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ignis.to_do.exception.CategoryException.CategoryAlreadyExistsException;
import com.ignis.to_do.exception.CategoryException.CategoryNotFoundException;
import com.ignis.to_do.model.Category;
import com.ignis.to_do.repository.CategoryRepository;

import jakarta.transaction.Transactional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category createCategory(Category category) {

        Optional<Category> existingCategory = categoryRepository.findByName(category.getName());
        if (existingCategory.isPresent()) {
            throw new CategoryAlreadyExistsException("Categoria com nome '" + category.getName() + "' já existe.");
        }
        return categoryRepository.save(category);
    }

    public Iterable<Category> getAllCategories() {
        return categoryRepository.findAll();
    }   
    
    public Category getCategoryById(Long categoryId) {

        return categoryRepository.findById(categoryId).orElseThrow(() -> 
        new CategoryNotFoundException("Categoria com ID " + categoryId + " não encontrada"));
    }

    public void verifiyCategory(Long categoryId) {
        categoryRepository.findById(categoryId).orElseThrow(() -> 
        new CategoryNotFoundException("Categoria com ID " + categoryId + " não encontrada"));
    }

    public void deleteCategoryById(Long categoryId) {
        verifiyCategory(categoryId);
        categoryRepository.deleteById(categoryId);
    }
    
    @Transactional
    public Category updateCategoryName(Category category) {
        verifiyCategory(category.getId());
        categoryRepository.updateCategoryName(category.getId(), category.getName());
        return categoryRepository.findById(category.getId()).get();
    }
}