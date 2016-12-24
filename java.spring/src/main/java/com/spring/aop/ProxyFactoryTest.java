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
        ProxyFactoryTest target = new ProxyFactoryTest();
        ProxyFactory factory = new ProxyFactory(target);
        factory.addAdvice(new MyAdvice());
        factory.setTarget(target);
        // JDK基于interface，此处必须使用interface不能使用实现类
        IProxyFactory proxy = (IProxyFactory) factory.getProxy();
        proxy.print();
    }

    public void print() {
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