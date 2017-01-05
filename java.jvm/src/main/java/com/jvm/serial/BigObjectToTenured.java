package com.jvm.serial;

import com.jvm.MemoryAllocation;

/**
 * Created by frinder6 on 2016/12/12.
 * <p>
 * 大对象直接到老年代
 */
public class BigObjectToTenured extends MemoryAllocation {

    /**
     * VM Args: -verbose:gc -Xmx50M -Xms50M -Xmn10M -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:SurvivorRatio=8 -XX:+UseSerialGC
     * <p>
     * 参数说明：heap大小50M，初始大小50M，年轻代大小10M（eden:survivor=8:1），使用 Serial 垃圾收集器
     */
    public static void main(String[] args) {
        System.out.println("begin...");
        byte[] arg1 = new byte[20 * L_1MB];
        System.out.println("end...");
    }

}
