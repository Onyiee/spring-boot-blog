package com.onyemowo.blog.controller;


import com.onyemowo.blog.dto.ApiResponse;
import com.onyemowo.blog.dto.PostDTO;
import com.onyemowo.blog.exception.PostException;
import com.onyemowo.blog.model.Post;
import com.onyemowo.blog.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3000)
@RestController
@Slf4j
@RequestMapping("api/v1/post")

public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/new")
    public ResponseEntity<?> createPost(@Valid @RequestBody PostDTO postDTO){
        log.info("Post DTO : {}", postDTO);
        postService.createPost(postDTO);
        return new ResponseEntity<>(new ApiResponse(true,"Post successfully created"), HttpStatus.CREATED);
    }

    @DeleteMapping("/deletePostById/{Id}")
    public ResponseEntity<?> deletePostById( @Valid @PathVariable String Id){
        log.info("Id : {} ", Id);
        try{
            postService.deletePostById(Id);
            return new ResponseEntity<>(new ApiResponse(true,"deleted post successfully"),
                    HttpStatus.NO_CONTENT);
        } catch (PostException postException){
            return new ResponseEntity<>(new ApiResponse(false, postException.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/updatePostById/{id}")
        public ResponseEntity<?> updatePost(@PathVariable @RequestBody String id, PostDTO updatedPostDetails){
        log.info("post DTO : {} \n id : {}", updatedPostDetails, id);
        try{
            PostDTO updatedPost = postService.updatePost(id,updatedPostDetails);
            return new ResponseEntity<>(updatedPost, HttpStatus.OK);
        }catch (PostException postException){
            return new ResponseEntity<>(new ApiResponse(false,  postException.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getPostById(@Valid @PathVariable String id){
        log.info("id : {}", id);
        try {
          PostDTO postDTO = postService.getPostById(id);
            return new ResponseEntity<>(postDTO,
                    HttpStatus.FOUND);
        }catch (PostException postException){
            return new ResponseEntity<>(new ApiResponse(false, postException.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/all")
    public ResponseEntity<?> getAllPosts(){
        try{
            List<PostDTO> allPosts = postService.getAllPosts();
            return new ResponseEntity<>(allPosts, HttpStatus.FOUND);
        }catch (PostException postException){
            return new ResponseEntity<>(new ApiResponse(false, postException.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getByTitle/{title}")
    public ResponseEntity<?> getPostByTitle(@Valid @PathVariable String title){
        log.info("title : {}", title);
        try {
            PostDTO post =postService.getPostByTitle(title);
            return new ResponseEntity<>(post,
                    HttpStatus.FOUND);
        }catch (PostException postException){
            return new ResponseEntity<>(new ApiResponse(false, postException.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }

}
