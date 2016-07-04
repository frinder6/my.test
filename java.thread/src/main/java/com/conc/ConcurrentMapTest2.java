package com.conc;

import com.alibaba.fastjson.JSON;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by frinder_liu on 2016/6/23.
 */
public class ConcurrentMapTest2 {

    private Object lock = new Object();

    private final Map<String, AtomicInteger> map = new ConcurrentHashMap<>();

    private int index = 100;

    public ConcurrentMapTest2(int index) {
        this.index = index;
    }

    public void increase() {
        String threadName = "main";
        synchronized (lock) {
            AtomicInteger ai = map.get(threadName);
            if (null == ai) {
                ai = new AtomicInteger(1);
            } else {
                ai.incrementAndGet();
            }
            map.put(threadName, ai);
        }
    }


    public static void createThread(final ConcurrentMapTest2 test) {
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
        final ConcurrentMapTest2 test = new ConcurrentMapTest2(index);
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
