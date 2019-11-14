package com.ysh.design.adapter;

/**
 * 适配器模式
 * 当存在两个相同的接口 而且他们的方法是一样 为了不修改原来定义好的接口和类
 * 适配器中的代码将接收你定义的接口然后产生你需要的接口，达到代码复用的效果
 *
 * @author joeysh
 * @date 2018/09/16 22:25
 */
public class Main {
    private static void call(Animal animal) {
        animal.call();
    }

    public static void main(String[] args) {
        call(new Dog());
        call(new Cat());
        call(new CallAdapter(new Car()));
        call(new CallAdapter(new Aircraft()));
    }
}
