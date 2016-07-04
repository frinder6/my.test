package com.pool;

import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created on 2016/5/4.
 */
public class ScheduledPoolTest {

    public static void main(String[] args) {
        int len = 3;

        // 定时任务线程池
        ScheduledThreadPoolExecutor executor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(len);

        // 添加3个任务，每5秒执行一次，并行处理
        for (int i = 0; i < 3; i++) {
            TaskThread task = new TaskThread(len, i);
            executor.scheduleAtFixedRate(task, 0L, 5L, TimeUnit.SECONDS);
        }

        /**
         * 手动关闭pool，没什么太大的用
         */
        try (
                Reader reader = new InputStreamReader(System.in);
                BufferedReader in = new BufferedReader(reader)
        ) {
            while (true) {
                String line = in.readLine();
                if (line.equalsIgnoreCase("no")) {
                    executor.shutdown();
                    break;
                }
            }
            System.out.println("close the pool");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}

/**
 * 模拟切片任务
 */
class TaskThread implements Runnable {

    private int increase = 0;
    private List<Integer> list;

    // 切片总数
    private int len;
    // 切片余数
    private int mod;
    private int size = 100;

    public TaskThread(int len, int mod) {
        this.len = len;
        this.mod = mod;
    }


    @Override
    public void run() {
        list = new ArrayList<>();
        for (int i = increase * size; i < (increase + 1) * size; i++) {
            // 根据 mod 分片
            if (i % len == mod) {
                list.add(i);
            }
        }
        increase++;
        System.out.println("**********************************************************");
        System.out.println(Thread.currentThread().getName() + " : " + JSON.toJSONString(list));
        System.out.println("**********************************************************");
    }

}
