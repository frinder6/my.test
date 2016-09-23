package com.redis.cluster2;

/**
 * Created by frinder6 on 2016/8/30.
 */
public interface RedisCluster2Service {

    void add(String key, Object value);

    String get(String key);

}
