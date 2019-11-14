package com.ysh.design.singletonpattern;

/**
 * 饿汉模式-单例对象构建方法
 *
 * @author joeysh
 * @date 2018/08/20 23:27
 */
public class Singleton2 {
    private static Singleton2 singleton;

    private Singleton2() {
    }

    /**
     * synchronized 代码块进行双重检查,可以提高性能
     *
     * @return
     */
    public static Singleton2 getInstance() {
        if (singleton == null) {
            //此处必须进行双重判断,否则依然存在线程安全问题
            synchronized (Singleton2.class) {
                if (singleton == null) {
                    return singleton = new Singleton2();
                }
            }
        }
        return singleton;
    }
}
