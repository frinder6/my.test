package com.redis.cluster2;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Created by frinder6 on 2016/8/30.
 */
public class RedisClusterFactory implements FactoryBean<JedisCluster>, InitializingBean {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private String address;
    private JedisCluster jedisCluster;
    private Integer timeout;
    private Integer maxRedirections;
    private GenericObjectPoolConfig genericObjectPoolConfig;

    private Pattern pattern = Pattern.compile("^.+[:]\\d{1,5}\\s*$");


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
            if (StringUtils.isEmpty(this.address)) {
                logger.error("[ redis config error ! ]");
                throw new IllegalArgumentException("[ redis config error, check please ! ]");
            }
            Set<HostAndPort> hostAndPorts = new HashSet<HostAndPort>();
            String[] addresses = StringUtils.split(this.address, ";");
            logger.info("[ redis cluster config host and port is : " + addresses + " ! ]");
            for (String addr : addresses) {
                boolean isHostPort = pattern.matcher(addr).matches();
                if (!isHostPort) {
                    throw new IllegalArgumentException("[ redis config : host or port is invalid, right format is [host:port], check it please ! ]");
                }
                String[] hp = StringUtils.split(addr, ":");
                HostAndPort hostAndPort = new HostAndPort(hp[0], Integer.parseInt(hp[1]));
                hostAndPorts.add(hostAndPort);
            }
            return hostAndPorts;
        } catch (Exception e) {
            logger.error("[ init redis error : " + e.getMessage() + " ]");
            e.printStackTrace();
        }
        return null;
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


    public void setAddress(String address) {
        this.address = address;
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
