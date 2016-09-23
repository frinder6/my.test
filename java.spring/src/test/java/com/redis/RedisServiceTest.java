package com.redis;

import com.alibaba.fastjson.JSON;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by frinder6 on 2016/8/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath:applicationContext-redis.xml"})
public class RedisServiceTest {

    @Resource(name = "redisService")
    private RedisServiceImpl redisService;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void test() {
//        this.redisService.add("key1", "hello world...");
//        System.out.println(this.redisService.get("key1"));
//
//        String[] arrs = {"abc", "123", "efg"};
//        this.redisService.add("list1", Arrays.asList(arrs));
//
//        System.out.println(this.redisService.get("list1"));

//        for (String str : arrs) {
//             this.redisService.add2List("list2", str);
//        }


//        this.redisService.add2List("list2", "hello world...");


//        List<Object> list22 = new ArrayList<Object>();
//        long len = this.redisService.getListSize("list2");
//        for (long i = 0; i < len; i++) {
//            list22.add(this.redisService.getListOne("list2", i));
//        }
//        System.out.println(JSON.toJSONString(list22));
//
//        List<?> list2 = this.redisService.getList("list2", 0, 100);
//        System.out.println(JSON.toJSONString(list2));
//
//        this.redisService.add2List("list3", Arrays.asList(new String[]{"hhh", "ddd"}));
//        list2 = this.redisService.getList("list3", 0, 100);
//        System.out.println(JSON.toJSONString(list2));

//        String[] sets = {"a", "b", "c"};
//        for (String s : sets) {
//            this.redisService.add2Set("set1", s);
//        }
//        this.redisService.add2Set("set1", "b");
//        System.out.println(JSON.toJSONString(this.redisService.getSet("set1")));
//        System.out.println(this.redisService.contains("set1", "a"));

        for (int i = 0; i < 5; i++) {
            this.redisService.add2Map("map1", "key" + i, "value" + i);
        }

        System.out.println(JSON.toJSONString(this.redisService.getMap("map1")));
        System.out.println(this.redisService.contains("map1", "key1"));

    }

}