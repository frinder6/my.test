package com.pool;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by frinder_liu on 2016/4/25.
 */
public class PoolTest1 {

    public static void main(String[] args) {
        System.out.println("主线程是：" + Thread.currentThread().getName());
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS,
//                new ArrayBlockingQueue<Runnable>(5));
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        for (int i = 1; i <= 15; i++) {
            MyTask myTask = new MyTask(i);
            executor.execute(myTask);
            System.out.println("线程池中线程数目：" + executor.getPoolSize() + "，队列中等待执行的任务数目：" +
                    executor.getQueue().size() + "，已执行完别的任务数目：" + executor.getCompletedTaskCount());
        }
        executor.shutdown();
    }
}


class MyTask implements Runnable {
    private int taskNum;

    public MyTask(int num) {
        this.taskNum = num;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " 正在执行task " + taskNum);
        try {
            Thread.currentThread().sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("task " + taskNum + "执行完毕");
    }
}