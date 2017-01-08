package com.executor.re;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by frinder6 on 2017/1/8.
 */
public class QTest {

    /**
     * 执行任务服务
     */
    public static ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);

    /**
     * 保存执行任务
     */
    public static BlockingDeque<WorkUnit<String>> workUnitBlockingDeque = new LinkedBlockingDeque<>();


    static {
        WorkUnit<String> workUnit;
        for (int i = 0; i <= 100; i++) {
            workUnit = new WorkUnit<>("string:" + i);
            workUnitBlockingDeque.add(workUnit);
        }
    }

    /**
     * @return
     */
    public static ScheduledFuture<?> execute() {
        ScheduledFuture<?> future = executorService.scheduleAtFixedRate(() -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println(workUnitBlockingDeque.poll().getWork());
            int l = new Random().nextInt(100);
            System.out.println(l);
            try {
                Thread.sleep(l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 10, 10, TimeUnit.MICROSECONDS);

        return future;
    }

    public static void main(String[] args) throws InterruptedException {
        for (WorkUnit<String> workUnit : workUnitBlockingDeque) {
            // System.out.println(workUnit.getWork());
        }
        System.out.println("-----------------------------------");
        System.out.println(Thread.currentThread().getName());
        execute();

    }

}
