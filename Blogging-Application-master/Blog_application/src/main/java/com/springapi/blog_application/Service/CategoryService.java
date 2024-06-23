package com.springapi.blog_application.Service;

import com.springapi.blog_application.Payloads.CategoryDto;
import java.util.List;


public interface CategoryService {

    // CREATE
    CategoryDto createCategory(CategoryDto categoryDto);
    // UPDATE
    CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId );
    // DELETE
    void deleteCategory(Integer categoryId);
    // GET_All
    List<CategoryDto> getAllCategories();
    // GET
    CategoryDto getCategoryById(Integer categoryId);
}
