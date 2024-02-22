package com.URLShortener.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "shortened_urls")
@Data
public class ShortenedURL {
    @Id
    private String shortUrl;
    private String originalUrl;

    // Getters and setters
}

