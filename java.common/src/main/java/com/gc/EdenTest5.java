package com.gc;

/**
 * Created by frinder_liu on 2016/5/11.
 */
public class EdenTest5 {

    private static final int _1MB = 1024 * 1024;

    /**
     * VM Args: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseSerialGC
     * -XX:MaxTenuringThreshold=15      --晋升老年代的年龄
     * -XX:+PrintTenuringDistribution
     *
     * @param args
     */
    public static void main(String[] args) {
        byte[] allocaton1, allocaton2, allocaton3, allocaton4;
        allocaton1 = new byte[_1MB / 4];
        allocaton2 = new byte[_1MB / 4];
        allocaton3 = new byte[4 * _1MB];
        allocaton4 = new byte[4 * _1MB];
        allocaton4 = null;
        allocaton4 = new byte[4 * _1MB];

    }

}
