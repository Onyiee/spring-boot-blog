package com.onyemowo.blog.service;

import com.onyemowo.blog.dto.PostDTO;
import com.onyemowo.blog.exception.PostException;
import com.onyemowo.blog.model.Post;
import com.onyemowo.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    PostRepository postRepository;

    @Override
    public void createPost(PostDTO postDTO) {
        Post post = PostDTO.unpackDTO(postDTO);
        createPost(post);
    }

    private void createPost(Post post){
        postRepository.save(post);
    }

    @Override
    public List<PostDTO> getAllPosts() {
        List<PostDTO> postDTOS = new ArrayList<>();
        for (Post post : getAllPostsFromDb()){
            postDTOS.add(PostDTO.packDTO(post));
        }
        return postDTOS;
    }

    private List<Post> getAllPostsFromDb(){
        return postRepository.findAll();
    }

    @Override
    public PostDTO getPostById(String id) throws PostException {
        return PostDTO.packDTO(findPostById(id));
    }


    private Post findPostById(String id) throws PostException {
        Optional<Post> postOptional = postRepository.findById(id);
        if (postOptional.isPresent()){
            return postOptional.get();
        }else{
            throw new PostException("No post found with this id");
        }
    }

    @Override
    public PostDTO getPostByTitle(String title) throws PostException{
        return PostDTO.packDTO(findPostByTitle(title));
    }


    private Post findPostByTitle(String title) throws PostException {
        Optional<Post> postOptional = postRepository.findByTitle(title);
        if (postOptional.isPresent()){
            return postOptional.get();
        }else{
            throw new PostException("No post found with this title");
        }
    }

    @Override
    public void deletePostById(String Id) throws PostException {
       Post postToDelete = findPostById(Id);
       deletePost(postToDelete);
    }


    private void deletePost(Post post){
        postRepository.delete(post);
    }

    @Override
    public PostDTO updatePost(String id, PostDTO updatedPostDetails) throws PostException {
        Post postToUpdate = findPostById(id);
        if (!postToUpdate.getTitle().equals(updatedPostDetails.getTitle()))
            postToUpdate.setTitle(updatedPostDetails.getTitle());
        if (!postToUpdate.getBody().equals(updatedPostDetails.getBody()))
            postToUpdate.setBody(updatedPostDetails.getBody());
        if (!postToUpdate.getPublishDate().equals(updatedPostDetails.getPublishDate()))
            postToUpdate.setPublishDate(updatedPostDetails.getPublishDate());
        if (!postToUpdate.getAuthorId().equals(updatedPostDetails.getAuthorId()))
            postToUpdate.setAuthorId(updatedPostDetails.getAuthorId());

        Post updatedPost = savePost(postToUpdate);
        return PostDTO.packDTO(updatedPost);
    }

    private Post savePost(Post post){
        return postRepository.save(post);
    }


}
