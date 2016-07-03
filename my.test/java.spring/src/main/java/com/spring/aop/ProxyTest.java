package com.spring.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by frinder_liu on 2016/6/24.
 */
public class ProxyTest {

    public static void main(String[] args) {
        InvocationHandler handler = new Proxy2Handler();
        Proxy2 proxy2 = (Proxy2) Proxy.newProxyInstance(Proxy2Impl.class.getClassLoader(), Proxy2Impl.class.getInterfaces(), handler);
        proxy2.say();
    }
}


interface Proxy2 {
    void say();
}

class Proxy2Impl implements Proxy2 {
    public void say() {
        System.out.println("hello proxy...");
    }
}

class Proxy2Handler implements InvocationHandler {

    private Object object;

    public Proxy2Handler() {
        super();
        this.object = new Proxy2Impl();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        doBefore();
        Object result = method.invoke(object, args);
        doAfter();
        return result;
    }

    private void doBefore() {
        System.out.println("-----------------------------");
        System.out.println("before handler...");
        System.out.println("-----------------------------");
    }


    private void doAfter() {
        System.out.println("*****************************");
        System.out.println("after handler...");
        System.out.println("*****************************");
    }
}