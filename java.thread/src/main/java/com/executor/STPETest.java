package com.executor;

import java.util.concurrent.*;

/**
 * Created by frinder_liu on 2016/4/20.
 */
public class STPETest {

    private ThreadPoolExecutor executor;
    private ScheduledExecutorService stpe;
    private ScheduledFuture<?> hndl;

    private BlockingDeque<WorkUnit<String>> lbq = new LinkedBlockingDeque<>();


    public void run() {
        stpe = Executors.newScheduledThreadPool(2);
        final Runnable msgReader = new Runnable() {
            @Override
            public void run() {
                String nextMsg = lbq.poll().getWork();
                if (nextMsg != null) {
                    System.out.println("Msg recvd : " + nextMsg);
                }
            }
        };
        hndl = stpe.scheduleAtFixedRate(msgReader, 10, 10, TimeUnit.MICROSECONDS);
    }

    public void cancel() {
        final ScheduledFuture<?> myHndl = hndl;
        stpe.schedule(new Runnable() {
            @Override
            public void run() {
                myHndl.cancel(true);
            }
        }, 10, TimeUnit.MILLISECONDS);
    }

}

class WorkUnit<T> {
    private final T work;

    public T getWork() {
        return work;
    }

    public WorkUnit(T work) {
        this.work = work;
    }
}

