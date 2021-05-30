package com.example.spring.service.redisService;

import com.example.spring.entity.Product;
import com.example.spring.exception.ApiException;
import com.example.spring.repository.RedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedisServiceProduct {
    @Autowired
    RedisRepository redisRepository;
    public Product setProduct(Product product) throws ApiException {
        try {
           return  redisRepository.setObjectRedis(product);
        }catch (Exception e){
            throw new ApiException("loi roi thang ngu");
        }
    }
}
