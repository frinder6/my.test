package com.queue;

/**
 * Created by frinder_liu on 2016/4/20.
 */
public abstract class Pet {

    private final String name;

    public Pet(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void examine();
}

