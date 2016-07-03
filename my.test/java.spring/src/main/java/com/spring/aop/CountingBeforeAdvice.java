package com.spring.aop;

import org.springframework.aop.MethodBeforeAdvice;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * Created by frinder_liu on 2016/6/23.
 */
public class CountingBeforeAdvice extends MethodCounter implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {

    }
}

