package com.jvm;

/**
 * Created by frinder_liu on 2016/5/10.
 */
public class StackOutOfMemoryTest {

    private void dontStop() {
        while (true) {

        }
    }


    public void stackLeakByThread() {
        while (true) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            });
            t.start();
        }
    }


    /**
     * VM Args: -Xss2M
     *
     * @param args
     */
    public static void main(String[] args) {
        StackOutOfMemoryTest test = new StackOutOfMemoryTest();
        test.stackLeakByThread();
    }
}
