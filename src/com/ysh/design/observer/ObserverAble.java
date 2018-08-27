package com.ysh.design.observer;

/**
 * 抽象被观察者角色：也就是一个抽象主题，它把所有对观察者对象的引用保存在一个集合中，每个主题都可以有任意数量的观察者。
 * 抽象主题提供一个接口，可以增加和删除观察者角色。一般用一个抽象类和接口来实现。
 *
 * @author yangshenghong
 * @date 2018-08-27
 */
public interface ObserverAble {
    /**
     * 注册用户的信息
     *
     * @param o
     */
    void registerObserver(Observer o);

    /**
     * 从服务信息中移除指定用户信息
     *
     * @param o
     */
    void removeObserver(Observer o);
}
