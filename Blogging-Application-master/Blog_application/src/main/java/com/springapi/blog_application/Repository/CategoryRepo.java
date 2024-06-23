package com.springapi.blog_application.Repository;


import com.springapi.blog_application.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
}
