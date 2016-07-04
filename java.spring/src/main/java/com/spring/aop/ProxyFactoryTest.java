package com.spring.aop;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultBeanFactoryPointcutAdvisor;

import java.lang.reflect.Method;

/**
 * Created by frinder_liu on 2016/6/30.
 */
public class ProxyFactoryTest implements IProxyFactory {

    public static void main(String[] args) {
//        ProxyFactoryTest test = new ProxyFactoryTest();
//        ProxyFactory factory = new ProxyFactory(test);
//        factory.addAdvice(new MyAdvice());
//        factory.addAdvisor(new DefaultBeanFactoryPointcutAdvisor());
//        ProxyFactoryTest tProxy = (ProxyFactoryTest) factory.getProxy();
//        tProxy.print();
        ProxyFactoryTest target = new ProxyFactoryTest();
        ProxyFactory factory = new ProxyFactory(target);
        factory.addAdvice(new MyAdvice());
        factory.setTarget(target);
        ProxyFactoryTest proxy = (ProxyFactoryTest)factory.getProxy();
        proxy.print();
    }

    public void print(){
        System.out.println("test...");
    }

}

interface IProxyFactory {
    void print();
}


class MyAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("before mehtod...");
    }
}