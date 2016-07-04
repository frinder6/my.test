package com.jvm;

/**
 * Created by frinder_liu on 2016/5/10.
 */
public class StackOverflowTest {

    private int stackLength = 1;

    public void stackLeak(){
        stackLength++;
        stackLeak();
    }

    /**
     * VM Args: -Xss128k
     * @param args
     */
    public static void main(String[] args) {
        StackOverflowTest test = new StackOverflowTest();
        test.stackLeak();
    }
}
