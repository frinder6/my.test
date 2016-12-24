package com.jvm.serial;

import com.jvm.MemoryAllocation;

/**
 * Created by frinder6 on 2016/12/12.
 * <p>
 * serial out of memory 测试
 */
public class OutOfMemory extends MemoryAllocation {

    /**
     * VM Args: -verbose:gc -Xmx20M -Xms20M -Xmn10M -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:SurvivorRatio=8 -XX:+UseSerialGC -Xloggc:oom.log -XX:+HeapDumpOnOutOfMemoryError
     * <p>
     * 参数说明：heap大小20M，初始大小20M，年轻代大小10M（eden:survivor=8:1），使用 Serial 垃圾收集器
     */
    public static void main(String[] args) throws Exception {
        System.out.println("begin...");
        byte[] b1 = new byte[1 * L_1MB];
        byte[] b2 = new byte[1 * L_1MB];
        byte[] b3 = new byte[1 * L_1MB];
        byte[] b4 = new byte[1 * L_1MB];
        byte[] b5 = new byte[1 * L_1MB];
        byte[] b6 = new byte[1 * L_1MB];
        byte[] b7 = new byte[1 * L_1MB];
        byte[] b8 = new byte[1 * L_1MB];
        byte[] b9 = new byte[1 * L_1MB];
        byte[] b10 = new byte[1 * L_1MB];
        byte[] b11 = new byte[1 * L_1MB];
        byte[] b12 = new byte[1 * L_1MB];
        byte[] b13 = new byte[1 * L_1MB];
        byte[] b14 = new byte[1 * L_1MB];
        byte[] b15 = new byte[1 * L_1MB];
        byte[] b16 = new byte[1 * L_1MB];
        byte[] b17 = new byte[1 * L_1MB];
        byte[] b18 = new byte[1 * L_1MB];
        byte[] b19 = new byte[1 * L_1MB];
        byte[] b20 = new byte[1 * L_1MB];
        System.out.println("end...");
    }

}
