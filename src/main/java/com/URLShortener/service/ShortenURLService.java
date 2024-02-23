package com.URLShortener.service;

import com.URLShortener.exception.ApplicationException;
import com.URLShortener.model.ShortenedURL;
import com.URLShortener.repo.ShortenedURLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class ShortenURLService {
    private static long counter = 1000000000;
    private static final String ALGORITHM = "SHA-256";
    private static final int STRING_LENGTH = 8;

    @Autowired
    private ShortenedURLRepository shortenedURLRepository;
    @Autowired
    private RedisService redisService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public ShortenedURL shortenURL(String originalUrl) throws Exception {

        String uniqueString = generateUniqueString(originalUrl);

        // Save the mapping in the cache
        redisService.setToRedis(originalUrl, uniqueString);

        // Save shortened URL to MongoDB
        ShortenedURL shortenedURL = new ShortenedURL();
        shortenedURL.setOriginalUrl(originalUrl);
        shortenedURL.setShortUrl(uniqueString);
        shortenedURLRepository.save(shortenedURL);

        return shortenedURL;
    }
    public String generateUniqueString(String originalUrl) throws Exception {
        String uniqueString;
        do {
            uniqueString = generateRandomString(originalUrl);
        } while (stringExists(uniqueString, originalUrl)); // Check if the generated string already exists
        return uniqueString;
    }

    private static String generateRandomString(String originalUrl) {
//        String input = Long.toString(counter);
        String input = originalUrl;
        try {
            MessageDigest digest = MessageDigest.getInstance(ALGORITHM);
            byte[] hash = digest.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            // Take the first 8 characters for uniqueness
            return hexString.toString().substring(0, STRING_LENGTH);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            // Handle exception
            return null;
        }
    }

    private Boolean stringExists(String uniqueString, String originalUrl) throws Exception {
        // Check if the generated string already exists in the database
        Optional<ShortenedURL> shortenedURL = shortenedURLRepository.findById(uniqueString);
        if(shortenedURL.isPresent()){
            throw new ApplicationException("500", String.format("Shortened URL already exists for original URL: %s. Shortened URL: %s", originalUrl, uniqueString));
        }
        return false;
    }

    public ShortenedURL getOriginalUrl(String shortUrl) throws ApplicationException {
        // Check if the short URL exists in the cache
        String cachedOriginalUrl = redisTemplate.opsForValue().get(shortUrl);
        if (cachedOriginalUrl != null) {
            // If it exists, return the cached short URL
            ShortenedURL shortenedURL = new ShortenedURL();
            shortenedURL.setOriginalUrl(cachedOriginalUrl);
            shortenedURL.setShortUrl(shortUrl);
            return shortenedURL;
        }
        Optional<ShortenedURL> shortenedURL = shortenedURLRepository.findById(shortUrl);
        if(shortenedURL.isEmpty()){
            throw new ApplicationException("501", String.format("the shortUrl for originalUrl: %s does not exist in DB", shortUrl));
        }
//        redisTemplate.opsForValue().set(shortenedURL.get().getOriginalUrl(), shortenedURL.get().getShortUrl(), 1, TimeUnit.HOURS);

        redisService.setToRedis(shortenedURL.get().getOriginalUrl(), shortenedURL.get().getShortUrl());
        return shortenedURL.get();
    }
}

