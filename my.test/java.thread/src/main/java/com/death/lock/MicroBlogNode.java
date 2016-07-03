package com.death.lock;

import com.unchange.obj.UnChangeObjDemo;

/**
 * Created by frinder_liu on 2016/4/19.
 */
public class MicroBlogNode {
    private final String ident;

    public MicroBlogNode(String ident) {
        this.ident = ident;
    }

    public synchronized void propagateUpdate(UnChangeObjDemo demo, MicroBlogNode node) {
        System.out.println(ident + "  " + demo.toString() + "  " + node.ident);
        node.confirmUpdate(this, demo);
    }

    public synchronized void confirmUpdate(MicroBlogNode node, UnChangeObjDemo demo) {
        System.out.println(ident + "  " + node.ident + "  " + demo.toString());
    }

    public static void main(String[] args) {
        final MicroBlogNode local = new MicroBlogNode("local");
        final MicroBlogNode other = new MicroBlogNode("other");

        final UnChangeObjDemo first = new UnChangeObjDemo("1", "1");
        final UnChangeObjDemo second = new UnChangeObjDemo("2", "2");

        new Thread(new Runnable() {
            @Override
            public void run() {
                local.propagateUpdate(first, other);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                other.propagateUpdate(second, local);
            }
        }).start();

    }
}
