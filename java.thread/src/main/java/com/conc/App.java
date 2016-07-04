package com.conc;

/**
 * Created by frinder_liu on 2016/6/23.
 */
public class App {

    public static void main(String[] args) {
        int index = 10_000_000;
        ConcurrentMapTest.test(index);
        ConcurrentMapTest2.test(index);
        ConcurrentMapTest3.test(index);
    }

}
