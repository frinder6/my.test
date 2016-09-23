package com.spring.ioc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.ResourceEntityResolver;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by frinder_liu on 2016/6/23.
 */
@Component
public class DynamicLoadBeanUtil implements ApplicationContextAware, ApplicationListener {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private AbstractApplicationContext applicationContext;


    /**
     * 动态加载xml
     * @param locations
     */
    public void dynamicLoadBeanByXml(String... locations) {
        DefaultListableBeanFactory factory = (DefaultListableBeanFactory) this.applicationContext.getBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.setResourceLoader(this.applicationContext);
        reader.setEntityResolver(new ResourceEntityResolver(this.applicationContext));
        reader.loadBeanDefinitions(locations);
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = (AbstractApplicationContext) applicationContext;
    }

    public AbstractApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        logger.info("**********************************");
        logger.info(event.toString());
        logger.info("**********************************");
    }
}
