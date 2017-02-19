package com.thread;

/**
 * thread yeild 方法测试
 * <p>
 * yield方法：当前线程由 运行状态变为 就绪状态，所有线程（包括当前线程）重新竞争资源运行
 * <p>
 * Created by frinder6 on 2017/1/10.
 */
public class ThreadYieldTest {

    public static void main(String[] args) {

        /**
         * 模拟业务线程1
         */
        Thread t1 = new Thread(() -> {
            int i = 0;
            while (true) {
                try {
                    System.out.println(Thread.currentThread().getName() + " " + i);
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                } finally {
                    if (i == 50) {
                        break;
                    }
                    ++i;
                }
            }
        });
        t1.start();

        /**
         * 模拟业务线程2
         */
        Thread t2 = new Thread(() -> {
            for (int i = 0; i <= 50; i++) {
                System.out.println(Thread.currentThread().getName() + " " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
                if (i % 5 == 0) {
                    /**
                     * 当遇到5的倍数时，使当前线程回归就绪状态，重新竞争执行资源
                     */
                    Thread.yield();
                }
            }
        });
        t2.start();


    }

}
