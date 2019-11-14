package com.ysh.design.observer;

/**
 * 具体观察者角色：实现抽象观察者角色所需要的更新接口，一边使本身的状态与制图的状态相协调。
 *
 * @author yangshenghong
 * @date 2018-08-27
 */
public class User implements Observer {
    private String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + " 收到推送消息： " + message);
    }

}
