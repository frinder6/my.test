package com.jvm;

import org.junit.Test;

public class MemoryAllocationTest {

    @Test
    public void avg() {
        Integer[] ints = {15302, 13250, 13168, 14785, 13777, 13703, 13468, 13607, 12809, 14574};
        int total = 0;
        for (int i : ints) {
            total += i;
        }
        System.out.println(total / ints.length);
    }

}