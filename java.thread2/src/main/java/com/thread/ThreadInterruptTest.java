package com.thread;

import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * thread interrupt 方法测试
 * <p>
 * interrupt 方法：如果线程被Object.wait，Thread.join和Thread.sleep三种方法之一阻塞，
 * 它将接收到一个中断异常（InterruptedException），从而提早地终结被阻塞状态。
 * interrupt方法并不是强制终止线程，它只能设置线程的interrupted状态，
 * 被block的线程(sleep() or join())在被调用interrupt时会产生InterruptException，此时是否终止线程由本线程自己决定。
 * <p>
 * Created by frinder6 on 2017/1/9.
 */
public class ThreadInterruptTest {

    public static void main(String[] args) throws InterruptedException {

        /**
         * 模拟处理业务线程
         */
        Thread t1 = new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " : start sleep...");
                Thread.sleep(10000);
            } catch (InterruptedException e) {
            } finally {
                System.out.println();
                System.out.println(Thread.currentThread().getName() + " : end sleep...");
            }
        });
        t1.start();

        /**
         * 后台守护线程监控业务线程
         */
        Thread t2 = new Thread(() -> {
            long time = 0, step = 500;
            // 如果业务线程处理等待状态，认为其仍在休眠
            while (Thread.State.TIMED_WAITING.equals(t1.getState())) {
                try {
                    Thread.sleep(step);
                    time += step;
                } catch (InterruptedException e) {
                } finally {
                    System.out.println(Thread.currentThread().getName() + " waiting：" + time + " ms");
                }
            }
        });
        t2.setDaemon(true);
        t2.start();

        /**
         * 模拟中断业务线程线程，5s后中断业务线程
         */
//        Thread t3 = new Thread(() -> {
//            System.out.println(Thread.currentThread().getName());
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//            } finally {
//                t1.interrupt();
//            }
//        });
//        t3.start();


        /**
         * 模拟处理业务线程2
         */
        Thread t4 = new Thread(() -> {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                String line = reader.readLine();
                while (!StringUtils.isEmpty(line)) {
                    System.out.println(Thread.currentThread().getName() + " is running..." + line);
                    line = reader.readLine();
                }
            } catch (IOException e) {
            }
        });
        t4.start();

        /**
         * 主线程中中断业务线程
         */
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        } finally {
            // 中断业务线程1（sleep方法），中断成功
            t1.interrupt();
            // 中断业务线程2（非wait/sleep/join方法），中断失败
            t4.interrupt();
        }

    }

}
