package com.queue;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class LinkedBlockingDequeTest {

    public static void main(String[] args) {
//        PetAdapter catAdapter = new PetAdapter(new Cat("cat"));
//        PetAdapter dogAdapter = new PetAdapter(new Dog("dog"));
//        pets.add(catAdapter);
//        pets.add(dogAdapter);
        BlockingDeque<PetAdapter<Pet>> pets = new LinkedBlockingDeque<>(10);
        NewPatientPet p = new NewPatientPet(pets, 2000);
        Veterinarian v = new Veterinarian(pets, 1000);
        new Thread(p).start();
        new Thread(v).start();
    }

}


class Veterinarian implements Runnable {

    protected final BlockingDeque<PetAdapter<Pet>> pets;
    protected String text = "";
    protected final int restTime;
    private boolean shutdown = false;
    private boolean init = true;

    public Veterinarian(BlockingDeque<PetAdapter<Pet>> pets, int restTime) {
        this.pets = pets;
        this.restTime = restTime;
    }

    public synchronized void shutdown() {
        shutdown = true;
    }

    @Override
    public void run() {
        while (!shutdown) {
            seePatient();
            try {
                if (pets.isEmpty() && !init) {
                    shutdown = true;
                    System.out.println("all over, work off!");
                } else {
                    System.out.println("have a rest!");
                    Thread.sleep(restTime);
                }
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
                shutdown = true;
            }
            //  if (init) init = false;
        }
    }

    public void seePatient() {
        try {
            PetAdapter<Pet> pet = pets.take();
            Pet p = pet.getPet();
            p.examine();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            shutdown = true;
        }
    }
}


class NewPatientPet implements Runnable {

    protected final BlockingDeque<PetAdapter<Pet>> pets;
    protected final int restTime;
    private boolean shutdown = false;

    public NewPatientPet(BlockingDeque<PetAdapter<Pet>> pets, int restTime) {
        this.pets = pets;
        this.restTime = restTime;
    }

    public synchronized void shutdown() {
        shutdown = true;
    }

    @Override
    public void run() {
        PetAdapter<Pet> adapter;
        int i = 0;
        while (!shutdown) {
            if (i % 2 == 0) {
                adapter = new PetAdapter(new Cat("cat" + i));
            } else {
                adapter = new PetAdapter(new Dog("dog" + i));
            }
            try {
//                boolean accept = pets.offer(adapter);
                boolean accept = pets.offer(adapter, 1000, TimeUnit.MILLISECONDS);
                if (accept) {
                    System.out.println("A new come, total : " + pets.size());
                } else {
                    System.out.println("OH, the queue is full!");
                }
                Thread.sleep(restTime);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                shutdown = true;
            }
//            if (i == 20) {
//                shutdown = true;
//            }
            i++;
        }

    }
}
