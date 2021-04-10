package com.onyemowo.blog.dto;

import com.onyemowo.blog.model.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class PostDTO {
    private String title;
    private String body;
    private String publishDate;
    private String authorId;

    public static Post unpackDTO(PostDTO postDTO){
        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setBody(postDTO.getBody());
        post.setPublishDate(postDTO.getPublishDate());
        post.setAuthorId(postDTO.getAuthorId());
        return post;
    }

    public static  PostDTO packDTO(Post post){
        PostDTO postDTO = new PostDTO();
        postDTO.setTitle(post.getTitle());
        postDTO.setBody(post.getBody());
        postDTO.setPublishDate(post.getPublishDate());
        postDTO.setAuthorId(post.getAuthorId());
        return postDTO;
    }

}
