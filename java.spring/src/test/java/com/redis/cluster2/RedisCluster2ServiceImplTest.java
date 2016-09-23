package com.redis.cluster2;

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
@ContextConfiguration({"classpath:applicationContext-redis-cluster6.xml"})
public class RedisCluster2ServiceImplTest {

    @Resource(name="redisCluster2Service")
    private RedisCluster2Service redisCluster2Service;

    @Test
    public void test(){
        String key = "key_test";
        String value = "I am a small bird...";
        this.redisCluster2Service.add(key, value);
        System.out.println(this.redisCluster2Service.get(key));
    }

}