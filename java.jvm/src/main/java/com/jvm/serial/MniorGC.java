package com.jvm.serial;

import com.jvm.MemoryAllocation;

/**
 * Created by frinder6 on 2016/12/12.
 * <p>
 * serial minor gc测试
 * <p>
 * minor gc : eden区域的gc
 */
public class MniorGC extends MemoryAllocation {

    /**
     * VM Args: -verbose:gc -Xmx20M -Xms20M -Xmn10M -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:SurvivorRatio=8 -XX:+UseSerialGC
     * <p>
     * 参数说明：heap大小20M，初始大小20M，年轻代大小10M（eden:survivor=8:1），使用 Serial 垃圾收集器
     */
    public static void main(String[] args) throws Exception {
//        System.in.read();
        System.out.println("begin...");
        byte[] arg1, arg2, arg3, arg4;
        System.out.println("0...");
        arg1 = new byte[4 * L_1MB];
        System.out.println("1...");
        arg2 = new byte[4 * L_1MB];
        System.out.println("2...");
        arg3 = new byte[5 * L_1MB];
        System.out.println("3...");
        arg4 = new byte[2 * L_1MB];
        System.out.println("4...");
        System.out.println("end...");
    }

}
