package com.springapi.blog_application.Repository;

import com.springapi.blog_application.Model.Category;
import com.springapi.blog_application.Model.Post;
import com.springapi.blog_application.Model.User;
import com.springapi.blog_application.Payloads.PostDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {

    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);

//    List<Post> findByTitleContaining(String title);
}
