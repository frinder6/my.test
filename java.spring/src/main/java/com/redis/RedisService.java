package com.redis;

/**
 * Created by frinder6 on 2016/8/27.
 */
public interface RedisService {

    void add(String key, Object value);

    Object get(String key);

}
