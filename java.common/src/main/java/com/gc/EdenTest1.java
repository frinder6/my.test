package com.gc;

/**
 * Created by frinder_liu on 2016/5/11.
 */
public class EdenTest1 {

    private static final int _1MB = 1024 * 1024;

    /**
     * VM Args: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseSerialGC
     * -XX:+UseSerialGC     --使用 Serial GC
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {
        byte[] allocaton1, allocaton2, allocaton3, allocaton4;
        allocaton1 = new byte[5 * _1MB];
        allocaton2 = new byte[4 * _1MB];
        allocaton3 = new byte[3 * _1MB];
        allocaton4 = new byte[4 * _1MB];

    }

}
