package com.method.handler;

/**
 * Created by frinder_liu on 2016/4/27.
 */
public class JavapTest {

    private static final int _P_1 = 1;
    public static final int _P_2 = 2;

    public static void main(String[] args) {
        int m = 5, n = 0;
        for (int i = 0; i < 10; i++) {
            m = m++;
            n = ++n;
        }
        System.out.println("m = " + m);
        System.out.println("n = " + n);
    }


    private void say() {
        System.out.println("hello world...");
    }
}
