package com.spring.aop;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MethodCounter implements Serializable {

    private Map<String, Integer> map = new ConcurrentHashMap<>();

    private AtomicInteger totalCount = new AtomicInteger(0);

    public void count(Method method) {
        count(method.getName());
    }

    public synchronized void count(String methodName) {
        Integer i = map.get(methodName);
        i = (i == null) ? 1 : ++i;
        map.put(methodName, i);
    }

    public Integer getTotalCount() {
        return totalCount.get();
    }

    public Integer getCallCount(String methodName) {
        Integer i = map.get(methodName);
        return (i == null) ? 0 : i;
    }

}
