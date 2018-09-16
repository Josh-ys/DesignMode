package com.ysh.design.adapter;

/**
 * @author joeysh
 * @date 2018/09/16 22:25
 */
public class Aircraft implements Vehicle {
    @Override
    public void call() {
        System.out.println("aircraft call");
    }
}
