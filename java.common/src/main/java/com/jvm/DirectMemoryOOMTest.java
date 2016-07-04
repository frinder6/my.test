package com.jvm;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by frinder_liu on 2016/5/10.
 */
public class DirectMemoryOOMTest {

    private static final int _1MB = 1024 * 1024;

    /**
     * VM Args: -Xmx20M -XX:MaxDirectMemorySize=10M
     * @param args
     * @throws IllegalAccessException
     */
    public static void main(String[] args) throws IllegalAccessException {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true) {
            unsafe.allocateMemory(_1MB);
        }
    }
}
