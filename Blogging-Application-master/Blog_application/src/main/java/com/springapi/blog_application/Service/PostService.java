package com.springapi.blog_application.Service;

import com.springapi.blog_application.Model.Post;
import com.springapi.blog_application.Payloads.PostDto;
import com.springapi.blog_application.Payloads.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    PostDto updatePost(PostDto postDto, Integer postId);
    void deletePost(Integer postDto);
    PostResponse getAllPosts(Integer pageNumber , Integer pageSize, String sortBy, String sortDir);
    PostDto getSinglePosts(Integer postId);

    // get all post by category
    List<PostDto> getAllPostsByCategory(Integer categoryId);

    // GET ALL POST BY USER
    List<PostDto> gellAllPostByUser(Integer userId);

    // SEAERC POSTT
//    List<PostDto> getPostByKeyword(String keyword);
}
