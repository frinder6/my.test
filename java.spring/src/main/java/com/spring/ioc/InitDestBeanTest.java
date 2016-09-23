package com.spring.ioc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created by frinder_liu on 2016/6/22.
 */
@Component
public class InitDestBeanTest implements InitializingBean, DisposableBean {


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private int index = 0;

    public InitDestBeanTest() {
        logger.info("InitDestBeanTest ：" + ++index);
    }

    public void initMethod() {
        logger.info("initMethod ：" + ++index);
    }

    @PostConstruct
    public void postConstruct() {
        logger.info("PostConstruct ：" + ++index);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("afterPropertiesSet ：" + ++index);
    }

    public void destoryMethod() {
        logger.info("destoryMethod ：" + ++index);
    }

    @PreDestroy
    public void preDestroy() {
        logger.info("preDestroy ：" + ++index);
    }

    @Override
    public void destroy() throws Exception {
        logger.info("destroy ：" + ++index);
    }


}
