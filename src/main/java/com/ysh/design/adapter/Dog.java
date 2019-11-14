package com.ysh.design.adapter;

/**
 * @author joeysh
 * @date 2018/09/16 22:23
 */
public class Dog implements Animal {
    @Override
    public void call() {
        System.out.println("dog call");
    }
}
