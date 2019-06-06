package com.arouter.javapoet.sigleton;

/**
 * 反射能否打破单例？
 * 　　首先，对外部类的私有构造器中加入 instance==null 的判断，防止反射入侵外部类。
 * 　　其次，静态内部类保证了从外部很难获取 SingletonHolder 的 Class 对象，从而保证了内部类不会被反射。
 * 多线程能否打破单例？
 * 　　Holder 模式借用了饿汉模式的优势，就是在加载类（内部类）的同时对 instance 对象进行初始化。
 * 　　由于自始至终类只会加载一次，所以即使在多线程的情况下，也能够保持单例的性质。
 * <p>
 * 优势？劣势？
 * 　　优势：兼顾了懒汉模式的内存优化（使用时才初始化）以及饿汉模式的安全性（不会被反射入侵）。
 * 　　劣势：需要多加载一个类；相比于懒汉模式，Holder 创建的单例，只能通过 JVM 去控制器生命周期，不能手动 destroy。
 */
public class UserModel {
    private UserModel() {
        if (SingletonHolder.instance != null) {
            throw new IllegalStateException();
        }
    }

    private static class SingletonHolder {
        private static UserModel instance = new UserModel();
    }

    public static UserModel getInstance() {
        return SingletonHolder.instance;
    }
}
