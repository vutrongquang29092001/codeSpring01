package com.example.spring.repository;

import com.example.spring.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RedisRepository {
    @Autowired
    RedisTemplate redisTemplate;
    public Product setObjectRedis(Product product){
        redisTemplate.opsForValue().set(product.getProductID(),product);
        return (Product) redisTemplate.opsForValue().get("product");
    }


}
