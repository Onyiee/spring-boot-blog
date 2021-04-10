package com.onyemowo.blog.service;

import com.onyemowo.blog.dto.PostDTO;
import com.onyemowo.blog.exception.PostException;
import com.onyemowo.blog.model.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
     void createPost(PostDTO postDTO);
     List<PostDTO> getAllPosts() throws PostException;
     PostDTO getPostById(String id) throws PostException;
     PostDTO getPostByTitle(String title) throws PostException;
     void deletePostById(String Id) throws PostException;
     PostDTO updatePost(String id, PostDTO postDTO) throws PostException;
}
