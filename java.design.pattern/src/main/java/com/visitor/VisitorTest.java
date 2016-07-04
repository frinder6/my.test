package com.visitor;

import java.util.Enumeration;
import java.util.Vector;

/**
 * Created by frinder_liu on 2016/4/28.
 * 访问者模式（Visitor）
 * <p/>
 * 访问者模式就是一种分离对象数据结构与行为的方法，
 * 通过这种分离，可达到为一个被访问者动态添加新的操作而无需做其它的修改的效果
 */
public class VisitorTest {

    public static void main(String[] args) {
        SubjectStructure structure = new SubjectStructure();
        Visitor visitor = new VisitorImpl();
        structure.add(new SubjectImpl());
        structure.add(new SubjectImpl2());
        structure.action(visitor);
    }
}

class SubjectStructure {
    private Vector<Subject> subjects = new Vector<>();

    public void action(Visitor visitor) {
        Enumeration<Subject> subs = subjects.elements();
        while (subs.hasMoreElements()) {
            subs.nextElement().accept(visitor);
        }
    }


    public void add(Subject subject) {
        subjects.add(subject);
    }

    public void remove(Subject subject) {
        subjects.remove(subject);
    }
}


/**
 * 目标对象
 */
interface Subject {
    /**
     * 目标对象接受访问者访问
     *
     * @param visitor
     */
    void accept(Visitor visitor);

    String getSubject();
}

/**
 * 访问者
 */
interface Visitor {
    /**
     * 访问者访问目标对象
     *
     * @param subject
     */
    void visit(Subject subject);
}

class VisitorImpl implements Visitor {

    @Override
    public void visit(Subject subject) {
        System.out.println("visit the subject: " + subject.getSubject());
    }
}

class SubjectImpl implements Subject {

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String getSubject() {
        return "haha...";
    }
}

class SubjectImpl2 implements Subject {

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String getSubject() {
        return "wuwu...";
    }
}