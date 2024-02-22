package com.URLShortener.repo;

import com.URLShortener.model.ShortenedURL;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShortenedURLRepository extends MongoRepository<ShortenedURL, String> {
    boolean existsByShortUrl(String shortUrl);
}
