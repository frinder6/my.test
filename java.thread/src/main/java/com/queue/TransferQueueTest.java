package com.queue;

import java.util.concurrent.*;

/**
 * Created by frinder_liu on 2016/4/20.
 */
public class TransferQueueTest {

    public static void main(String[] args) throws InterruptedException {
        TransferQueue<PetAdapter<Pet>> pets = new LinkedTransferQueue<>();
        NewPatientPet2 p = new NewPatientPet2(pets, 100);
        Veterinarian2 v = new Veterinarian2(pets, 200);
        new Thread(p).start();
        Thread.sleep(1000);
        new Thread(v).start();
    }
}


class Veterinarian2 implements Runnable {

    protected final TransferQueue<PetAdapter<Pet>> pets;
    protected String text = "";
    protected final int restTime;
    private boolean shutdown = false;

    public Veterinarian2(TransferQueue<PetAdapter<Pet>> pets, int restTime) {
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
                Thread.sleep(restTime);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
                shutdown = true;
            }
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


class NewPatientPet2 implements Runnable {

    protected final TransferQueue<PetAdapter<Pet>> pets;
    protected final int restTime;
    private boolean shutdown = false;

    public NewPatientPet2(TransferQueue<PetAdapter<Pet>> pets, int restTime) {
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
                boolean accept = pets.tryTransfer(adapter, 100, TimeUnit.MILLISECONDS);
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
            i++;
        }

    }
}