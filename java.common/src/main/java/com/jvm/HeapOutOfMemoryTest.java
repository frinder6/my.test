package com.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * Heap 溢出测试
 * Created on 2016/5/9.
 */
public class HeapOutOfMemoryTest {

    /**
     * VM Args: -Xms20M -Xmx20M -XX:+HeapDumpOnOutOfMemoryError
     *
     * @param args
     */
    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }
    }

    static class OOMObject {

    }
}
