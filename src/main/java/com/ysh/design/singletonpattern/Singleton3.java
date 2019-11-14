package com.ysh.design.singletonpattern;

/**
 * 静态内部类-单例对象构建方法
 * <p>
 * 利用JDK的特性：类级内部类只有在第一次被使用的时候才被会装载，
 * 这样可以保证单例对象只有在第一次被使用的时候初始化一次，
 * 并且不需要加锁，性能得到大大提高，并且保证了线程安全。
 *
 * @author joeysh
 * @date 2018/08/20 23:31
 */
public class Singleton3 {
    private Singleton3() {

    }

    private static class InitSingleton {
        private static Singleton3 singleton = new Singleton3();
    }

    public static Singleton3 getInstance() {
        return InitSingleton.singleton;
    }
}
