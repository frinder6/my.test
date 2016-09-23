package com.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * Created by frinder6 on 2016/8/27.
 */
@Service("redisService")
public class RedisServiceImpl {

    @Resource(name = "redisTemplate")
    public RedisTemplate<String, Object> redisTemplate;


    /**
     * 添加 Object
     *
     * @param key
     * @param value
     */
    public void add(String key, Object value) {
        this.redisTemplate.opsForValue().set(key, value);
    }


    /**
     * 获取 Object
     *
     * @param key
     * @return
     */
    public Object get(String key) {
        return this.redisTemplate.opsForValue().get(key);
    }


    /**
     * 添加到 list
     *
     * @param key
     * @param value
     */
    public void add2List(String key, Object value) {
        this.redisTemplate.opsForList().leftPush(key, value);
    }

    /**
     * 获取集合长度
     *
     * @param key
     * @return
     */
    public long getListSize(String key) {
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
    public List<?> getList(String key, long start, long end) {
        return this.redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 获取指定位置值
     *
     * @param key
     * @param index
     * @return
     */
    public Object getListOne(String key, long index) {
        return this.redisTemplate.opsForList().index(key, index);
    }


    /**
     * 向set中添加对象
     *
     * @param key
     * @param value
     */
    public void add2Set(String key, Object value) {
        this.redisTemplate.opsForSet().add(key, value);
    }


    /**
     * 获取 set 中所有对象
     *
     * @param key
     * @return
     */
    public Set<Object> getSet(String key) {
        return this.redisTemplate.opsForSet().members(key);
    }

    /**
     * 是否是 set 中的对象
     *
     * @param key
     * @param value
     * @return
     */
    public boolean contains(String key, Object value) {
        return this.redisTemplate.opsForSet().isMember(key, value);
    }

    /**
     * 获取集合长度
     *
     * @param key
     * @return
     */
    public long getSetSize(String key) {
        return this.redisTemplate.opsForSet().size(key);
    }


    /**
     * 添加到 map
     *
     * @param key
     * @param mk
     * @param value
     */
    public void add2Map(String key, String mk, Object value) {
        this.redisTemplate.opsForHash().put(key, mk, value);
    }


    /**
     * 获取 map
     *
     * @param key
     * @return
     */
    public Object getMap(String key) {
        return this.redisTemplate.opsForHash().entries(key);
    }

    /**
     * map中是否包含key
     *
     * @param key
     * @param mk
     * @return
     */
    public boolean contains(String key, String mk) {
        return this.redisTemplate.opsForHash().entries(key).containsKey(mk);
    }


}
