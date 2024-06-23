package com.springapi.blog_application.Controller;

import com.springapi.blog_application.ApiResponse.Apiresponse;
import com.springapi.blog_application.Payloads.PostDto;
import com.springapi.blog_application.Payloads.PostResponse;
import com.springapi.blog_application.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private PostService postService;

    // Create post
    @PostMapping("/users/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable("userId") Integer userId, @PathVariable("categoryId") Integer categoryId) {
        PostDto createdPost = postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<PostDto>(createdPost, HttpStatus.CREATED);
    }

    // get list of post by UserId
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostByUserId(@PathVariable("userId") Integer userId) {
      List<PostDto> posts = postService.gellAllPostByUser(userId);
      return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
    }

    // get list of post by user
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostByCategoryId(@PathVariable("categoryId") Integer categoryId) {
        List<PostDto> posts = postService.getAllPostsByCategory(categoryId);
        return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
    }

    // GET ALL POST
    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value ="pageNumber" , defaultValue = "1", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir

    ) {
        PostResponse posts = postService.getAllPosts(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<PostResponse>(posts, HttpStatus.OK);
    }

    // GET POST BY ID
    @GetMapping("/post/{postId}")
    public ResponseEntity<PostDto> getSinglePost(@PathVariable Integer postId) {
        PostDto posts = postService.getSinglePosts(postId);
        return new ResponseEntity<PostDto>(posts, HttpStatus.OK);
    }

    // Delete post by Id
    @DeleteMapping("/post/{postId}")
    public Apiresponse deletepost(@PathVariable Integer postId) {
        postService.deletePost(postId);
        return new Apiresponse("Post Delete Successfully", true);
    }

    // update post
    @PutMapping("/post/{postId}")
    public ResponseEntity<PostDto> updatePost(@PathVariable Integer postId, @RequestBody PostDto postDto) {
        PostDto updatePost = postService.updatePost(postDto,postId);
        return new ResponseEntity< PostDto>(updatePost, HttpStatus.OK);
    }


    // Search by keyword
//    @GetMapping("search/{keyword}")
//    public ResponseEntity<List<PostDto>> searchByKeyword(@PathVariable("keyword") String keyword) {
//        List<PostDto> postbykey = postService.getPostByKeyword(keyword);
//        return new ResponseEntity<List<PostDto>>(postbykey, HttpStatus.OK);
//    }
}
