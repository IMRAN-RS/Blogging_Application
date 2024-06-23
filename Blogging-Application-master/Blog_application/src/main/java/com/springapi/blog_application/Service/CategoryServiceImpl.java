package com.springapi.blog_application.Service;

import com.springapi.blog_application.Exception.ResourceNotFoundException;
import com.springapi.blog_application.Model.Category;
import com.springapi.blog_application.Payloads.CategoryDto;
import com.springapi.blog_application.Repository.CategoryRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category cat = this.modelMapper.map(categoryDto, Category.class);
        Category createCategory = this.categoryRepo.save(cat);
        return this.modelMapper.map(createCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category cat = categoryRepo.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("CategoriesDto","categorrie_id", categoryId));

        cat.setCategorytitle(categoryDto.getCategorytitle());
        cat.setCategoryDescription(categoryDto.getCategoryDescription());

        Category upaatecate = this.categoryRepo.save(cat);

        return this.modelMapper.map(upaatecate, CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category", "Category_id" , categoryId));
        categoryRepo.delete(category);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> category = this.categoryRepo.findAll();
        List<CategoryDto> categoryDtos = category.stream().map( (cat)-> modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
        return categoryDtos;
    }

    @Override
    public CategoryDto getCategoryById(Integer categoryId) {
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category", "Category_id" , categoryId));

        return this.modelMapper.map(category, CategoryDto.class);
    }
}
