package com.queue;

public class PetAdapter<T> {
    private final T pet;

    public T getPet() {
        return pet;
    }

    public PetAdapter(T pet) {
        this.pet = pet;
    }
}
