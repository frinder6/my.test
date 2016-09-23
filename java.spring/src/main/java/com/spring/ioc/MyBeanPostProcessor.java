package com.spring.ioc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * Created by frinder_liu on 2016/6/22.
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        logger.info("----------------------------------------------");
        logger.info("postProcessBeforeInitialization ：" + beanName);
        logger.info("----------------------------------------------");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        logger.info("----------------------------------------------");
        logger.info("postProcessAfterInitialization ：" + beanName);
        logger.info("----------------------------------------------");
        return bean;
    }


}
