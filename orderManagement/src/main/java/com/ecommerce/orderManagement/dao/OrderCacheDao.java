package com.ecommerce.orderManagement.dao;

import com.ecommerce.orderManagement.models.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class OrderCacheDao {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final int EXPIRATION_TIME = 10; // 10 seconds

    public void saveOrder(String key, OrderDto orderDto) {
        redisTemplate.opsForValue().set(key, orderDto, EXPIRATION_TIME, TimeUnit.SECONDS);
    }

    public OrderDto getOrder(String key) {
        return (OrderDto) redisTemplate.opsForValue().get(key);
    }

    public void deleteOrder(String key) {
        redisTemplate.delete(key);
    }
}
