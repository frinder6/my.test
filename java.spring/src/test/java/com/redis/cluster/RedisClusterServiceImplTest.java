package com.redis.cluster;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

/**
 * Created by frinder6 on 2016/8/30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath:applicationContext-redis-cluster.xml"})
public class RedisClusterServiceImplTest {

    @Resource(name="redisClusterService")
    private RedisClusterService redisClusterService;


    @Test
    public void test(){
        String key = "key123";
        String value = "abcdefg...";
        this.redisClusterService.add(key, value);
        System.out.println(this.redisClusterService.get(key));
    }

}