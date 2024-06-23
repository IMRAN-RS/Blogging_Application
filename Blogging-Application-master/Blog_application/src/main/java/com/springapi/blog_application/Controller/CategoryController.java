package com.springapi.blog_application.Controller;

import com.springapi.blog_application.ApiResponse.Apiresponse;
import com.springapi.blog_application.Payloads.CategoryDto;
import com.springapi.blog_application.Service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // CREATE
    @PostMapping
    public ResponseEntity<CategoryDto> addCategory(@Valid @RequestBody CategoryDto categoryDto) {
        CategoryDto createCate = categoryService.createCategory(categoryDto);
        return new ResponseEntity<CategoryDto>(createCate, HttpStatus.CREATED);
    }

    // UPDATE
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> UpdateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable("categoryId") Integer categoryId) {
        CategoryDto updateCategory = categoryService.updateCategory(categoryDto, categoryId);
        return new ResponseEntity<CategoryDto>(updateCategory, HttpStatus.OK);
    }

    // DELETE
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer categoryId){
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<Apiresponse>(new Apiresponse("Deleted Successfully" , true), HttpStatus.OK);
    }

    // GET
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable("categoryId") Integer categoryId){
        CategoryDto getCategory = categoryService.getCategoryById(categoryId);
        return new ResponseEntity<CategoryDto>(getCategory,HttpStatus.OK);
    }

    // GET ALL
    @GetMapping("/allCategory")
    public ResponseEntity<List<CategoryDto>> getAllCategory(){
        List<CategoryDto> getAllCategory = categoryService.getAllCategories();
        return new ResponseEntity<List<CategoryDto>>(getAllCategory,HttpStatus.OK);
    }

}
