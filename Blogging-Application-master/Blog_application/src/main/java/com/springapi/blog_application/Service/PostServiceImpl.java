package com.springapi.blog_application.Service;

import com.springapi.blog_application.Exception.ResourceNotFoundException;
import com.springapi.blog_application.Model.Category;
import com.springapi.blog_application.Model.Post;
import com.springapi.blog_application.Model.User;
import com.springapi.blog_application.Payloads.PostDto;
import com.springapi.blog_application.Payloads.PostResponse;
import com.springapi.blog_application.Repository.CategoryRepo;
import com.springapi.blog_application.Repository.PostRepo;
import com.springapi.blog_application.Repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

//    @Autowired
//    private PostService postService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private PostRepo postRepo;

    // Create Post
    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

        User user = userRepo.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User Not Found","id",userId));

        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category Not Found","id",categoryId));


        Post post = modelMapper.map(postDto, Post.class);
        post.setImageName("Default.png");
        post.setPostDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post newPost = postRepo.save(post);

        return this.modelMapper.map(newPost, PostDto.class);
    }

    // Update Post
    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","id",postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());

        post = postRepo.save(post);

        PostDto updatedPostDto = this.modelMapper.map(post, PostDto.class);
        return updatedPostDto;
    }

    // Delete post by Id
    @Override
    public void deletePost(Integer postDto) {
        Post post = this.postRepo.findById(postDto).orElseThrow(()-> new ResourceNotFoundException("PostDto", "post", postDto));
        this.postRepo.delete(post);
    }

    @Override
    public PostResponse getAllPosts(Integer pageNumber , Integer pageSize, String sortBy, String sortDir) {

        Sort sort = null;
        if(sortDir.equalsIgnoreCase("asc")) {
            sort =Sort.by(sortBy).ascending();
        }
        else{
            sort =Sort.by(sortBy).descending();
        }


        Pageable p = PageRequest.of(pageNumber, pageSize, sort);

        Page<Post> pagepost = postRepo.findAll(p);
        List<Post> posts = pagepost.getContent();

        List<PostDto> postDtos = posts.stream().map((post)->modelMapper.map(post,PostDto.class)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagepost.getNumber());
        postResponse.setPageSize(pagepost.getSize());
        postResponse.setTotalElement(pagepost.getTotalElements());
        postResponse.setTotalPages(pagepost.getTotalPages());
        postResponse.setLastPage(pagepost.isLast());

        return postResponse;

    }

    @Override
    public PostDto getSinglePosts(Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post Not Found","id",postId));
        PostDto postDto = modelMapper.map(post, PostDto.class);
        return postDto;
    }

    @Override
    public List<PostDto> getAllPostsByCategory(Integer categoryId) {
        Category cat = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category Not Found","id",categoryId));
        List<Post> listofPost = postRepo.findByCategory(cat);

        List<PostDto> postDt = listofPost.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDt;
    }

    @Override
    public List<PostDto> gellAllPostByUser(Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User Not Found","id",userId));
        List<Post> listofPost = postRepo.findByUser(user);

       List<PostDto> postDtos = listofPost.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

//    @Override
//    public List<PostDto> getPostByKeyword(String keyword) {
//        List<Post> postByTitle = postRepo.findByTitleContaining(keyword);
//        List<PostDto> result= postByTitle.stream().map((post)->modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
//        return result;
//    }
}
