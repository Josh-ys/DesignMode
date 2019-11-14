package com.ysh.design.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxy implements InvocationHandler {
    private Object obj;

    public JDKProxy(Object obj) {
        this.obj = obj;
    }

    public Object createProxy() {
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("12");
        Object invoke = method.invoke(obj, args);
        System.out.println("34");
        return invoke;
    }
}
