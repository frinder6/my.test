package com.redis.cluster;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;
import redis.clients.jedis.ShardedJedisPool;

import javax.annotation.Resource;

/**
 * Created by frinder6 on 2016/8/30.
 */
@Service("redisClusterService")
public class RedisClusterServiceImpl implements RedisClusterService {

    @Resource(name = "shardedJedisPool")
    private ShardedJedisPool shardedJedisPool;

    public void add(String key, Object value) {
        this.shardedJedisPool.getResource().set(key, JSON.toJSONString(value));
    }


    public String get(String key) {
        return this.shardedJedisPool.getResource().get(key);
    }

}
