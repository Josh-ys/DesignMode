package com.ysh.design.observer;

/**
 * 抽象观察者角色：为所有的具体观察者定义一个接口，在得到主题通知时更新自己。
 *
 * @author yangshenghong
 * @date 2018-08-27
 */
public interface Observer {
    /**
     * 接收消息
     *
     * @param message
     */
    void update(String message);
}
