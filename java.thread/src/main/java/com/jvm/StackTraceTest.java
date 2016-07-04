package com.jvm;

import com.alibaba.fastjson.JSON;

import java.util.Map;

/**
 * Created by frinder_liu on 2016/5/19.
 */
public class StackTraceTest {

    public static void main(String[] args) {
        Map<Thread, StackTraceElement[]> traces = Thread.getAllStackTraces();
        for (Map.Entry<Thread, StackTraceElement[]> trace : traces.entrySet()) {
            Thread thread = trace.getKey();
            StackTraceElement[] elements = trace.getValue();
            System.out.print(thread.getName());
            System.out.print("  ");
            for (StackTraceElement element : elements) {
                System.out.print(JSON.toJSONString(element));
            }
            System.out.println();
        }
    }

}
