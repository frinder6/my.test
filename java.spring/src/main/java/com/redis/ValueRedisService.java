package com.redis;

import org.springframework.stereotype.Service;

/**
 * Created by frinder6 on 2016/8/29.
 */
@Service("valueRedisService")
public class ValueRedisService<T> extends BaseRedisService<T> {

    /**
     * 添加对象
     * @param key
     * @param value
     */
    public void add(String key, T value) {
        this.redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 获取对象
     * @param key
     * @return
     */
    public T get(String key) {
        return this.redisTemplate.opsForValue().get(key);
    }

}
