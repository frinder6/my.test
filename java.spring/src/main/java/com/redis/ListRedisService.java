package com.redis;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by frinder6 on 2016/8/29.
 */
@Service("listRedisService")
public class ListRedisService<T> extends BaseRedisService<T> {

    /**
     * 添加对象到集合
     * @param key
     * @param value
     */
    public void add(String key, T value) {
        this.redisTemplate.opsForList().leftPush(key, value);
    }


    /**
     * 获取集合长度
     * @param key
     * @return
     */
    public long getSize(String key) {
        return this.redisTemplate.opsForList().size(key);
    }

    /**
     * 获取集合
     *
     * @param key
     * @param start 开始索引（包含）
     * @param end   结束索引（包含）
     * @return
     */
    public List<T> get(String key, long start, long end) {
        return this.redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 获取指定位置值
     *
     * @param key
     * @param index
     * @return
     */
    public T getOne(String key, long index) {
        return this.redisTemplate.opsForList().index(key, index);
    }

}
