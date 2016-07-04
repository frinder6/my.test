package com.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by frinder_liu on 2016/5/19.
 */
public class MemoryTest {

    /**
     * -Xms100m -Xmx100m -XX:+UseSerialGC
     *
     * @param args
     * @throws InterruptedException
     */

    public static void main(String[] args) throws Exception {
        fillHeap(1000);
        System.out.println("over...");
        System.gc();
        System.in.read();
    }

    static class OOMObject {
        public byte[] placeholder = new byte[64 * 1024];
    }

    public static void fillHeap(int len) throws InterruptedException {
        List<OOMObject> list = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            Thread.sleep(50);
            list.add(new OOMObject());
        }
        // System.gc();
    }

}
