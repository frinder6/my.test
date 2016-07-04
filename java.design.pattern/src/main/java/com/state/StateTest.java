package com.state;

/**
 * Created by frinder_liu on 2016/4/28.
 * 状态模式（State）
 * <p/>
 * 核心思想就是：当对象的状态改变时，同时改变其行为，很好理解！
 * 就拿QQ来说，有几种状态，在线、隐身、忙碌等，每个状态对应不同的操作，而且你的好友也能看到你的状态，
 * 所以，状态模式就两点：1、可以通过改变状态来获得不同的行为。2、你的好友能同时看到你的变化。
 */
public class StateTest {

    public static void main(String[] args) {
        State state = new State();
        Control control = new Control(state);
        state.setAction(1);
        control.control();
        state.setAction(2);
        control.control();
        state.setAction(3);
        control.control();
    }
}

/**
 * 状态 类
 */
class State {
    private int action;

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public void doit() {
        System.out.println("do it...");
    }

    public void undo() {
        System.out.println("undo...");
    }
}

/**
 * 状态控制类
 */
class Control {
    private State state;

    public Control(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void control() {
        if (state.getAction() == 1) {
            state.doit();
        } else if (state.getAction() == 2) {
            state.undo();
        } else {
            System.out.printf("nothing to do...");
        }
    }
}
