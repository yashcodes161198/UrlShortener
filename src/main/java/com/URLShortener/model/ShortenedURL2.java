package com.URLShortener.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "shortened_urls_2")
@Data
public class ShortenedURL2 {
    @Id
    private String collidedUrl;
    private String originalUrl;
    private String shortUrl;

    // Getters and setters
}

