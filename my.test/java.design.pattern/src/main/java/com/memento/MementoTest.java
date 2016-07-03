package com.memento;

/**
 * Created by frinder_liu on 2016/4/28.
 * 备忘录模式（Memento）
 * <p/>
 * 主要目的是保存一个对象的某个状态，以便在适当的时候恢复对象，个人觉得叫备份模式更形象些，通俗的讲下：
 * 假设有原始类A，A中有各种属性，A可以决定需要备份的属性，备忘录类B是用来存储A的一些内部状态，类C呢，
 * 就是一个用来存储备忘录的，且只能存储，不能修改等操作
 */
public class MementoTest {

    public static void main(String[] args) {
        // 目标类
        Original original = new Original("abc");
        // 备忘目标类
        Storage storage = new Storage(original.createMemento());
        System.out.println(original.getValue());
        // 重置目标类的值
        original.setValue("xyz");
        System.out.println(original.getValue());
        // 从备忘中恢复目标类的值
        original.restoreMemento(storage.getMemento());
        System.out.println(original.getValue());
    }

}

/**
 * 备忘 类
 */
class Memento {
    private String value;

    public Memento(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

/**
 * 目标 类
 */
class Original {
    private String value;

    public Original(String value) {

        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    public Memento createMemento() {
        return new Memento(this.value);
    }

    public void restoreMemento(Memento memento) {
        this.value = memento.getValue();
    }
}

/**
 * 保存备忘 类
 */
class Storage {

    private Memento memento;

    public Storage(Memento memento) {
        this.memento = memento;
    }

    public Memento getMemento() {
        return memento;
    }

    public void setMemento(Memento memento) {
        this.memento = memento;
    }
}