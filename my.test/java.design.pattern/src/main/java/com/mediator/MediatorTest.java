package com.mediator;

/**
 * Created by frinder_liu on 2016/4/29.
 * 中介者模式（Mediator）
 * <p/>
 * 中介者模式也是用来降低类类之间的耦合的，因为如果类类之间有依赖关系的话，
 * 不利于功能的拓展和维护，因为只要修改一个对象，其它关联的对象都得进行修改。
 * 如果使用中介者模式，只需关心和Mediator类的关系，具体类类之间的关系及调度交给Mediator就行，这有点像spring容器的作用
 */
public class MediatorTest {

    public static void main(String[] args) {
        Mediator mediator = new MediatorImpl();
        mediator.createMediator();
        mediator.action();
    }
}

interface Mediator {
    void createMediator();

    void action();
}


class MediatorImpl implements Mediator {

    private User user1;
    private User user2;

    public User getUser1() {
        return user1;
    }

    public User getUser2() {
        return user2;
    }

    @Override
    public void createMediator() {
        user1 = new User1(this);
        user2 = new User2(this);
    }

    @Override
    public void action() {
        user1.work();
        user2.work();
    }
}


abstract class User {
    private Mediator mediator;

    public User(Mediator mediator) {
        this.mediator = mediator;
    }

    public Mediator getMediator() {
        return mediator;
    }

    abstract void work();
}


class User1 extends User {

    public User1(Mediator mediator) {
        super(mediator);
    }

    @Override
    void work() {
        System.out.println("user1 work...");
    }
}

class User2 extends User {

    public User2(Mediator mediator) {
        super(mediator);
    }

    @Override
    void work() {
        System.out.println("user2 work...");
    }
}