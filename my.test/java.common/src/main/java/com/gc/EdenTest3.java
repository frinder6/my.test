package com.gc;

/**
 * Created by frinder_liu on 2016/5/11.
 */
public class EdenTest3 {

    private static final int _1MB = 1024 * 1024;

    /**
     * VM Args: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseSerialGC
     * -XX:MaxTenuringThreshold=1      --晋升老年代的年龄
     * -XX:+PrintTenuringDistribution
     *
     * @param args
     */
    public static void main(String[] args) {
        byte[] allocaton1, allocaton2, allocaton3;
        allocaton1 = new byte[_1MB / 4];
        // 什么时候进入老年代取决于 XX:MaxTenuringThreshold 设置
        allocaton2 = new byte[4 * _1MB];
        allocaton3 = new byte[4 * _1MB];
        allocaton3 = null;
        allocaton3 = new byte[4 * _1MB];

    }

}
