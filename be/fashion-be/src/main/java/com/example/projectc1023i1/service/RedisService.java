package com.example.projectc1023i1.service;

import com.example.projectc1023i1.config.RedisConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RedisService {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    public void addTokenList(String username,String token) {
        ListOperations<String, String> ops = redisTemplate.opsForList();
        ops.rightPush(username,token);
    }

    public List<String> getTokenList(String username) {
        ListOperations<String,String> ops = redisTemplate.opsForList();
        return ops.range(username,0,-1);
    }

    public void removeTokenList(String username,String token) {
        ListOperations<String,String> ops = redisTemplate.opsForList();
        ops.leftPush(username,token);
    }

    public void deleteTokens(String username) {
        redisTemplate.delete(username);
    }

}
