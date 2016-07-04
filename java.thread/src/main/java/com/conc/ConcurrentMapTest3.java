package com.conc;

import com.alibaba.fastjson.JSON;
import com.google.common.util.concurrent.AtomicLongMap;

/**
 * Created by frinder_liu on 2016/6/23.
 */
public class ConcurrentMapTest3 {

    private final AtomicLongMap<String> map = AtomicLongMap.create();

    private final String KEY = "MAIN";

    private int index = 100;

    public ConcurrentMapTest3(int index) {
        this.index = index;
    }


    public void increase() {
        map.incrementAndGet(KEY);
    }


    public static void createThread(final ConcurrentMapTest3 test) {
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
        final ConcurrentMapTest3 test = new ConcurrentMapTest3(index);
        createThread(test);
        createThread(test);
        createThread(test);

        while (Thread.activeCount() > 2) {
        }

        long e = System.currentTimeMillis();
        System.out.println(e - b);

        System.out.println(JSON.toJSONString(test.map.asMap()));
    }


    public static void main(String[] args) throws InterruptedException {
        test(100);
    }

}
