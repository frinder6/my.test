package com.conc;

import com.alibaba.fastjson.JSON;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by frinder_liu on 2016/6/23.
 */
public class ConcurrentMapTest {

    private Object lock = new Object();

    private final Map<String, Integer> map = new ConcurrentHashMap<>();

    private int index = 100;

    public ConcurrentMapTest(int index) {
        this.index = index;
    }

    public void increase() {
        // String threadName = Thread.currentThread().getName();
        String threadName = "main";
        Integer i;
        synchronized (lock) {
            i = map.get(threadName);
            i = i == null ? 1 : ++i;
            map.put(threadName, i);
        }
    }


    public static void createThread(final ConcurrentMapTest test) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < test.index; i++) {
                    test.increase();
                }
            }
        }).start();
    }


    public static void test(int index) {
        long b = System.currentTimeMillis();
        final ConcurrentMapTest test = new ConcurrentMapTest(index);
        createThread(test);
        createThread(test);
        createThread(test);

        while (Thread.activeCount() > 2) {
        }

        long e = System.currentTimeMillis();
        System.out.println(e - b);

        System.out.println(JSON.toJSONString(test.map));
    }


    public static void main(String[] args) throws InterruptedException {
        test(100);
    }

}
