package com.ysh.design.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 具体被观察者角色：也就是一个具体的主题，在集体主题的内部状态改变时，所有登记过的观察者发出通知。
 *
 * @author yangshenghong
 * @date 2018-08-27
 */
public class MsgService implements ObserverAble {
    /**
     * 用于存放观察者角色
     */
    private List<Observer> list;


    private String message;

    public MsgService() {
        list = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer o) {
        list.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        if (!list.isEmpty()) {
            list.remove(o);
        }
    }

    public void setMsg(String s) {
        this.message = s;
        System.out.println("服务更新消息： " + s);
        //消息更新，通知所有观察者
        notifyObserver();
    }

    private void notifyObserver() {
        for (Observer observer : list) {
            observer.update(message);
        }
    }
}
