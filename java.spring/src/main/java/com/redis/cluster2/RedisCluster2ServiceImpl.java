package com.redis.cluster2;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;

/**
 * Created by frinder6 on 2016/8/30.
 */
@Service("redisCluster2Service")
public class RedisCluster2ServiceImpl implements RedisCluster2Service {

    @Resource(name="jedisCluster")
    private JedisCluster jedisCluster;

    public void add(String key, Object value){
        this.jedisCluster.set(key, JSON.toJSONString(value));
    }

    public String get(String key){
        return this.jedisCluster.get(key);
    }

}
