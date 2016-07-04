package com.singleton;

/**
 * Created by frinder_liu on 2016/4/28.
 */
public class SinglePatternTest {

    public static void main(String[] args) {
        // 饿汉式 调用
        SinglePattern.ehInstance.say();

        // 饿汉变种[推荐] 调用
        SinglePattern.getEhupInstance().say();

        // 饿汉变种2 调用
        SinglePattern.getEhup2Instance().say();

        // 懒汉式 调用
        SinglePattern.getLhInstance().say();

        // 懒汉式变种
        SinglePattern.getLhupInstance().say();

        // 静态内部类[推荐]
        SinglePattern.getInstance().say();

        // 双重校验锁[不推荐]
        SinglePattern.getDcInstance().say();

        // 枚举单例[极推荐]
        SingletonEnum.INSTANCE.say();
    }
}

/**
 * 枚举实现单例
 * 优点：
 * 1、实现简单
 * 2、线程安全（创建枚举默认就是线程安全）
 * 3、防止反序列化重新创建新的对象
 * <p/>
 * 缺点
 * 1、不是类，没有类的特性
 * 2、不能延迟加载
 */
enum SingletonEnum {

    INSTANCE;

    public void say() {
        System.out.println("hello world...");
    }
}


class SinglePattern {

    private SinglePattern() {
    }

    public void say() {
        System.out.println("hello world...");
    }


    /**
     * ----------------------------------------------------------------------
     * -------------------------------静态内部类------------------------------
     * ----------------------------------------------------------------------
     */

    /**
     * 静态内部类[极推荐]
     */
    private static class SingletonHolder {
        private static final SinglePattern instance = new SinglePattern();
    }

    public static final SinglePattern getInstance() {
        return SingletonHolder.instance;
    }


    /**
     * ----------------------------------------------------------------------
     * -------------------------------双重校验锁------------------------------
     * ----------------------------------------------------------------------
     */

    /**
     * 双重校验锁[不推荐]
     */
    private volatile static SinglePattern dcInstance;

    public static SinglePattern getDcInstance() {
        if (null == dcInstance) {
            synchronized (SinglePattern.class) {
                if (null == dcInstance) {
                    dcInstance = new SinglePattern();
                }
            }
        }
        return dcInstance;
    }


    /**
     * ----------------------------------------------------------------------
     * ---------------------------------懒汉式--------------------------------
     * ----------------------------------------------------------------------
     */

    /**
     * 懒汉式
     */
    private static SinglePattern lhInstance = null;

    public static SinglePattern getLhInstance() {
        if (lhInstance == null) {
            lhInstance = new SinglePattern();
        }
        return lhInstance;
    }


    /**
     * 懒汉式变种
     */
    public static synchronized SinglePattern getLhupInstance() {
        if (lhInstance == null) {
            lhInstance = new SinglePattern();
        }
        return lhInstance;
    }


    /**
     * ----------------------------------------------------------------------
     * ---------------------------------饿汉式--------------------------------
     * ----------------------------------------------------------------------
     */

    /**
     * 饿汉式(类加载的时候就创建实例)
     */
    public static final SinglePattern ehInstance = new SinglePattern();


    /**
     * 饿汉变种[推荐]
     */
    private static SinglePattern ehupInstance = new SinglePattern();

    public static SinglePattern getEhupInstance() {
        return ehupInstance;
    }


    /**
     * 饿汉变种2
     */
    private static SinglePattern ehup2Instance = null;

    static {
        ehup2Instance = new SinglePattern();
    }

    public static SinglePattern getEhup2Instance() {
        return ehup2Instance;
    }


}



