package com.queue;

public class Dog extends Pet {

    public Dog(String name) {
        super(name);
    }

    @Override
    public void examine() {
        System.out.println(this.getName() + " : Woof!");
    }
}
