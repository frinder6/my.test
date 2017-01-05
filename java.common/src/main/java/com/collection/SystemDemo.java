package com.collection;

import java.util.Arrays;

/**
 * Created by frinder6 on 2016/12/28.
 */
public class SystemDemo {

    /**
     * System.arraycopy() 方法分析
     *
     * @param args
     */
    public static void main(String[] args) {

        // 源数组
        String[] array = {"1", "2", "3", "4"};
        System.out.print("array : ");
        print(array);
        // 目标数组
        String[] copyArray = new String[5];
        // 将源数组全部复制到目标数组，并以索引1开始存放
        System.arraycopy(array, 0, copyArray, 1, array.length);
        System.out.print("copyArray : ");
        print(copyArray);

        // array长度+1
        array = Arrays.copyOf(array, array.length + 1);
        // 将array中数据向后移1位
        System.arraycopy(array, 0, array, 1, array.length - 1);
        System.out.print("array : ");
        print(array);
    }

    /**
     * @param array
     */
    private static void print(Object[] array) {
        for (Object s : array) {
            System.out.print(s + " ");
        }
        System.out.println();
    }

}
