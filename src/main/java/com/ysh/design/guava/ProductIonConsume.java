package com.ysh.design.guava;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

/**
 * guava 生产消费者模式测试
 *
 * @author yangshenghong
 * @date 2020-04-26
 */
@SuppressWarnings("all")
public class ProductIonConsume {
    public static void main(String[] args) {
        new Son();
        new Daughter();
        BaseParents.getEventBus().post("null");
        new Dog();
        new Cat();
        BaseAnimal.getEventBus().post("nul");
    }
}

@SuppressWarnings("all")
abstract class BaseParents {
    private static final EventBus EVENT_BUS = new EventBus();

    /**
     * 逻辑执行
     *
     * @param param the param
     */
    @Subscribe
    protected abstract void execute(String param);

    public BaseParents() {
        EVENT_BUS.register(this);
    }

    public static EventBus getEventBus() {
        return EVENT_BUS;
    }
}

class Son extends BaseParents {
    @Override
    protected void execute(String param) {
        System.out.println("I'm son...");
    }
}

class Daughter extends BaseParents {
    @Override
    protected void execute(String param) {
        System.out.println("I'm daughter...");
    }
}

@SuppressWarnings("all")
abstract class BaseAnimal {
    private static final EventBus EVENT_BUS = new EventBus();

    /**
     * 逻辑执行
     *
     * @param param the param
     */
    @Subscribe
    protected abstract void execute(String param);

    public BaseAnimal() {
        EVENT_BUS.register(this);
    }

    public static EventBus getEventBus() {
        return EVENT_BUS;
    }
}

class Dog extends BaseAnimal {
    @Override
    protected void execute(String param) {
        System.out.println("I'm dog...");
    }
}

class Cat extends BaseAnimal {
    @Override
    protected void execute(String param) {
        System.out.println("I'm cat...");
    }
}
