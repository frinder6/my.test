package com.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by frinder_liu on 2016/5/10.
 */
public class RuntimeConstantPoolOOM {

    /**
     * VM Args: -XX:PermSize=2M -XX:MaxPermSize=2M
     */
    public static void pgTest() {
        // 使用List保持常量池引用，避免Full GC回收常量池行为
        List<String> list = new ArrayList<>();
        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }

    public static void main(String[] args) {
        intern();
        pgTest();
    }

    public static void intern(){
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);

        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);
    }

}
