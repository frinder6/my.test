package com.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by frinder6 on 2016/8/29.
 */
@Service
public class BaseRedisService<T> {

    @Resource(name = "redisTemplate")
    public RedisTemplate<String, T> redisTemplate;


}
