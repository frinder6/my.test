package com.redis.cluster2;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.io.IOException;
import java.util.*;

/**
 * Created by frinder6 on 2016/8/30.
 */
public class RedisClusterFactoryCopy implements FactoryBean<JedisCluster>, InitializingBean {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private Resource addressConfig;
    private JedisCluster jedisCluster;
    private Integer timeout;
    private Integer maxRedirections;
    private GenericObjectPoolConfig genericObjectPoolConfig;

    public static void main(String[] args) {
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < 6; i++){
            Map<String, Object> map = new HashedMap();
            map.put("host", "10.199.195.228");
            map.put("port", 6379+i);
            list.add(map);
        }
        System.out.println(JSON.toJSONString(list));
//        String jsonStr = "[{\"host\":\"10.199.195.228\",\"port\":6379},{\"host\":\"10.199.195.228\",\"port\":6380},{\"host\":\"10.199.195.228\",\"port\":6381},{\"host\":\"10.199.195.228\",\"port\":6382},{\"host\":\"10.199.195.228\",\"port\":6383},{\"host\":\"10.199.195.228\",\"port\":6384}]";
//        List<Map<String, Object>> list = JSON.toJavaObject(JSON.parseArray(jsonStr), List.class);
//        System.out.println(list.size());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("[ begin to init redis config ! ]");
        Set<HostAndPort> hostAndPorts = postHandle();
        if (null != hostAndPorts && hostAndPorts.size() > 0) {
            jedisCluster = new JedisCluster(hostAndPorts, timeout, maxRedirections, genericObjectPoolConfig);
            logger.info("[ init redis config successfully ! ]");
        }
        logger.info("[ init redis over ! ]");
    }

    private Set<HostAndPort> postHandle() {
        try {
            Properties props = new Properties();
            props.load(addressConfig.getInputStream());
            Set<HostAndPort> hostAndPorts = new HashSet<HostAndPort>();
            for (Map.Entry<Object, Object> entry : props.entrySet()) {
                Object key = entry.getKey();
                if (null == key) {
                    logger.error("[ error config, please check ! ]");
                    break;
                }
                logger.info("[ init redis config key :" + key + " ]");
                if ("address".equalsIgnoreCase(key.toString())) {
                    String value = valueOf(entry.getValue());
                    logger.info("[ init redis config value : " + value + " ]");
                    HostAndPort hostAndPort;
                    JSONArray kvs = JSON.parseArray(value);
                    for (int i = 0; i < kvs.size(); i++) {
                        JSONObject kv = kvs.getJSONObject(i);
                        hostAndPort = new HostAndPort(kv.getString("host"), kv.getInteger("port"));
                        hostAndPorts.add(hostAndPort);
                    }
                    break;
                }
            }
            return hostAndPorts;
        } catch (IOException e) {
            logger.error("[ init redis error : " + e.getMessage() + " ]");
            e.printStackTrace();
        }
        return null;
    }

    private String valueOf(Object value) {
        return null == value ? "" : value.toString();
    }

    @Override
    public JedisCluster getObject() throws Exception {
        return jedisCluster;
    }

    @Override
    public Class<?> getObjectType() {
        return (this.jedisCluster != null ? this.jedisCluster.getClass() : JedisCluster.class);
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public void setAddressConfig(Resource addressConfig) {
        this.addressConfig = addressConfig;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public void setMaxRedirections(Integer maxRedirections) {
        this.maxRedirections = maxRedirections;
    }

    public void setGenericObjectPoolConfig(GenericObjectPoolConfig genericObjectPoolConfig) {
        this.genericObjectPoolConfig = genericObjectPoolConfig;
    }
}
