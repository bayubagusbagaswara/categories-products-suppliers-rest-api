package com.ecommerce.service;

import com.ecommerce.dto.category.*;
import com.ecommerce.exception.CategoryNotFoundException;

import java.util.List;

public interface CategoryService {

    CategoryResponseDto createCategory(CreateCategoryRequestDto createCategoryRequest);

    CategoryResponseDto getCategoryById(String id) throws CategoryNotFoundException;

    ListCategoryResponseDto getAllCategories(ListCategoryRequestDto listCategoryRequest);

    CategoryResponseDto updateCategory(String id, UpdateCategoryRequestDto updateCategoryRequest) throws CategoryNotFoundException;

    void deleteCategory(String id) throws CategoryNotFoundException;

    CategoryResponseDto getCategoryByName(String name) throws CategoryNotFoundException;

    List<CategoryResponseDto> getCategoryByNameContains(String name);

    List<CategoryResponseDto> getCategoryStartingWithName(String name);

    CategoryResponseDto getCategoryByProductId(String idProduct) throws CategoryNotFoundException;
}
