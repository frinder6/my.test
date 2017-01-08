package com.executor.re;

/**
 * Created by frinder6 on 2017/1/8.
 */
class WorkUnit<T> {
    private final T work;

    public T getWork() {
        return work;
    }

    public WorkUnit(T work) {
        this.work = work;
    }
}
