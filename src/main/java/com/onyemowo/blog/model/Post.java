package com.onyemowo.blog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Post {
    private String title;
    private String body;
    private String publishDate;
    private String authorId;
}
