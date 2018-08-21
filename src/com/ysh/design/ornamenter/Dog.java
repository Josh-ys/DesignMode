package com.ysh.design.ornamenter;

/**
 * 被装饰着
 *
 * @author joeysh
 * @date 2018/08/21 00:10
 */
public class Dog implements Animal {
    @Override
    public void eat() {
        System.out.println("dog eat bone...");
    }
}
