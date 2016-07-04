package com.gc;

/**
 * Created by frinder_liu on 2016/5/11.
 */
public class EdenTest2 {

    private static final int _1MB = 1024 * 1024;

    /**
     * VM Args: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseSerialGC
     * -XX:PretenureSizeThreshold=3145728   --当对象大于 3145728 时，直接进入老年代
     *
     * @param args
     */
    public static void main(String[] args) {
        byte[] allocaton1;
        allocaton1 = new byte[_1MB * 4]; // 直接分配在老年代



    }

}
