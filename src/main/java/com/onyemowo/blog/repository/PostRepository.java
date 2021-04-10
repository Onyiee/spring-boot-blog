package com.onyemowo.blog.repository;

import com.onyemowo.blog.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PostRepository extends MongoRepository<Post,String> {
    Optional<Post> findByTitle(String title);
}
