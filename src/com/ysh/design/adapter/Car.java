package com.ysh.design.adapter;

/**
 * @author joeysh
 * @date 2018/09/16 22:24
 */
public class Car implements Vehicle {
    @Override
    public void call() {
        System.out.println("car call");
    }
}
