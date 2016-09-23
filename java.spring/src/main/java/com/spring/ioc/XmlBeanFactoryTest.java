package com.spring.ioc;

import my.test.App;
import my.test.App2;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by frinder_liu on 2016/6/20.
 */
public class XmlBeanFactoryTest {


    public static void main(String[] args) {
//		ClassPathResource resource = new ClassPathResource("applicationContext.xml");
//		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
//		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
//		reader.loadBeanDefinitions(resource);
//		InitDestBeanTest test = (InitDestBeanTest)factory.getBean("initDestBeanTest");
//		test.print();

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//         InitDestBeanTest test = (InitDestBeanTest)context.getBean("initDestBeanTest");
         context.close();
//        DynamicLoadBeanUtil util = (DynamicLoadBeanUtil) context.getBean("dynamicLoadBeanUtil");
//
//        util.dynamicLoadBeanByXml("beans.xml");
//        App app = (App) context.getBean("app");
//        app.print();
//
//        util.dynamicLoadBeanByXml("beans2.xml");
//        App2 app2 = (App2)context.getBean("app");
//        app2.print();
    }

}
