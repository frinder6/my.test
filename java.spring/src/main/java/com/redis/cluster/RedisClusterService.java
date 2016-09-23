package com.redis.cluster;

/**
 * Created by frinder6 on 2016/8/30.
 */
public interface RedisClusterService {

    void add(String key, Object value);

    String get(String key);

}
