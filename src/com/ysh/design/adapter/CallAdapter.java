package com.ysh.design.adapter;

/**
 * 适配器
 *
 * @author joeysh
 * @date 2018/09/16 22:25
 */
public class CallAdapter implements Animal {
    private Vehicle vehicle;

    public CallAdapter(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public void call() {
        vehicle.call();
    }
}
