package com.conc;

/**
 * Created by frinder_liu on 2016/6/24.
 */
public class SingleTest {

    private SingleTest() {
    }

    public static final SingleTest INSTANCE = new SingleTest();


    public static void main(String[] args) {
        SingleTest t1 = SingleTest.INSTANCE;
        SingleTest t2 = SingleTest.INSTANCE;
        SingleTest t3 = SingleTest.INSTANCE;

        System.out.println(t1 == t2);
        System.out.println(t1 == t3);
        System.out.println(t2 == t3);
        System.out.println(t1);
        System.out.println(t2);
        System.out.println(t3);
    }

}
