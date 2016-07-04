package com.ticket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by frinder_liu on 2016/4/20.
 */
public class TicketThread implements Runnable {

    public final Map<String, Integer> map = new ConcurrentHashMap<>();

    private boolean isOver = false;

    private int tickets = 100;

    private Lock lock = new ReentrantLock();

    private void reduce() {
        lock.lock();
        try {
            String tname = Thread.currentThread().getName();
            if (tickets > 0) {
                tickets--;
                System.out.println(tname + ", the last count : " + tickets);
                if (map.containsKey(tname)) {
                    map.put(tname, map.get(tname) + 1);
                } else {
                    map.put(tname, 1);
                }
            } else {
                isOver = true;
            }
        } finally {
            lock.unlock();
        }
    }


//    private synchronized void reduce() {
//        String tname =
//                Thread.currentThread().getName();
//        if (tickets > 0) {
//            tickets--;
//            System.out.println(tname + ", the last count : " + tickets);
//            if
//                    (map.containsKey(tname)) {
//                map.put(tname, map.get(tname) + 1);
//            } else {
//                map.put(tname, 1);
//            }
//        } else {
//            isOver = true;
//        }
//    }


    @Override
    public void run() {
        while (!isOver) {
            reduce();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Test {
    public static void main(String[] args) {
        long beginTime = System.currentTimeMillis();
        TicketThread tt = new TicketThread();
        Thread t1 = new Thread(tt);
        t1.start();
        Thread t2 = new Thread(tt);
        t2.start();
        Thread t3 = new Thread(tt);
        t3.start();
        while (true) {
            if (t1.isAlive() && t2.isAlive() && t3.isAlive()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }
            for (Map.Entry<String, Integer> e : tt.map.entrySet()) {
                System.out.println(e.getKey() + " : " + e.getValue());
            }
            break;
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - beginTime);
    }
}