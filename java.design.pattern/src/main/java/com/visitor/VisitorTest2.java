package com.visitor;

/**
 * Created by frinder_liu on 2016/4/29.
 */
public class VisitorTest2 {

    public static void main(String[] args) {
        Horse wh = new WhiteHorse();
        Horse bh = new BlackHorse();
        Mozi m = new Mozi();
        m.ride(wh);
        m.ride(bh);
    }
}

class Mozi {
    public void ride(Horse horse){
        System.out.println("骑马");
    }

     public void ride(WhiteHorse horse){
        System.out.println("骑白马");
    }

     public void ride(BlackHorse horse){
        System.out.println("骑黑马");
    }


}

interface Horse {

}

class WhiteHorse implements Horse {

}

class BlackHorse implements Horse {

}