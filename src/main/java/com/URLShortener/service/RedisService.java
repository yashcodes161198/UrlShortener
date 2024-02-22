package com.URLShortener.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    public String getKey(String key){
        return null;
    }
    public void setToRedis(String originalUrl, String uniqueString) {
        redisTemplate.executePipelined((RedisConnection connection) -> {
            byte[] key = redisTemplate.getStringSerializer().serialize(uniqueString);
            byte[] value = redisTemplate.getStringSerializer().serialize(originalUrl);
            connection.set(key, value);
            connection.expire(key, 3600);
            return null;
        });
    }
}
