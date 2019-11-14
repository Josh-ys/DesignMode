package com.ysh.design.singletonpattern;

/**
 * 饿汉模式-单例对象构建方法
 * 类加载的时候，就进行对象的创建，系统开销较大，影响性能，所以多数采用懒汉模式，在使用时才真正的创建单例对象
 *
 * @author joeysh
 * @date 2018/08/20 23:23
 */
public class Singleton1 {
    private static Singleton1 singleton = new Singleton1();

    private Singleton1() {
    }

    private static Singleton1 getInstance() {
        return singleton;
    }
}
