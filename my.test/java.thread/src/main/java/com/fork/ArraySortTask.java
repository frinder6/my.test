package com.fork;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * Created by frinder_liu on 2016/4/20.
 */
public class ArraySortTask extends RecursiveAction {

    private final Integer[] arrs;

    public ArraySortTask(Integer[] arrs) {
        this.arrs = arrs;
    }

    @Override
    protected void compute() {
        System.out.println("begin to sort!");
        Arrays.sort(arrs);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end to sort!");
    }

    public Integer[] getArrs() {
        return arrs;
    }

}


class Test {
    public static void main(String[] args) {
        Integer[] arrs = {9, 3, 22, 55, 11, 88, 99, 100, 300, 111, 333, 400, 300};
        ArraySortTask task = new ArraySortTask(arrs);
        ForkJoinPool pool = new ForkJoinPool(4);
        long beginTime = System.currentTimeMillis();
        pool.invoke(task);
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - beginTime);

        for (int a : task.getArrs()) {
            System.out.print(a);
            System.out.print(" ");
        }

    }
}