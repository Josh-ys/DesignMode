package com.ysh.design.dynamicproxy;

import com.ysh.design.ornamenter.Animal;
import com.ysh.design.ornamenter.Dog;

/**
 * @author joeysh
 * @date 2018/08/21 01:06
 */
public class ProxyTest {
    public static void main(String[] args) {
        Animal animal = new Dog();
        animal = (Animal) DynamicProxy.createProxy(animal);
        animal.eat();
    }
}
