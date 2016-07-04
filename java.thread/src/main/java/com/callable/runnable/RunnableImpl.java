package com.callable.runnable;

/**
 * Created on 2016/5/18.
 */
public class RunnableImpl implements Runnable {

    public RunnableImpl(String acceptStr) {
        this.acceptStr = acceptStr;
    }

    private String acceptStr;

    @Override
    public void run() {
        try {
            // 线程阻塞 1 秒，此时有异常产生，只能在方法内部消化，无法上抛
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 最终处理结果无法返回
        System.out.println("hello : " + this.acceptStr);
    }


    public static void main(String[] args) {
        Runnable runnable = new RunnableImpl("my runable test!");
        long beginTime = System.currentTimeMillis();
        new Thread(runnable).start();
        long endTime = System.currentTimeMillis();
        System.out.println("cast : " + (endTime - beginTime) / 1000 + " second!");
    }
}
