package com.method.handler;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * Created by frinder_liu on 2016/4/26.
 */
public class MethodHandlerTest {
    public static void main(String[] args) {
        MethodHandle handle;
        MethodType type = MethodType.methodType(void.class, String.class);
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        try {
            handle = lookup.findVirtual(MethodHandlerEvent.class, "say", type);
            handle.invoke(new MethodHandlerEvent(), "jack");
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}


class MethodHandlerEvent {

    public static final String _INT = "8";

    public void say(String name) {
        System.out.println(name + " say : hello world...");
    }
}