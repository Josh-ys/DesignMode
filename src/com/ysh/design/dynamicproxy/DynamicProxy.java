package com.ysh.design.dynamicproxy;

import java.lang.reflect.Proxy;

/**
 * 在运行 ，在 JVM 内部动态生成 class 字节码对象(Class 对象)
 * Jdk 动态代理只针对于接口操作.
 * <p>
 * jdk 的动态代理只可以为接口去完成操作，而 cglib 它可以为没有实现接口的类去做代
 * 理，也可以为实现接口的类去做代理.
 * <p>
 * JDK动态代理和CGLIB字节码生成的区别:
 * （1）JDK动态代理只能对实现了接口的类生成代理，而不能针对类
 * （2）CGLIB是针对类实现代理，主要是对指定的类生成一个子类，覆盖其中的方法
 * 因为是继承，所以该类或方法最好不要声明成final
 * <p>
 * Jdk动态代理  代理对象与目标对象什么关系？    兄弟关系！(会生成代理类，InvocationHandler作为参数调用构造方法来获得代理类的实例)
 * Cglib       代理对象与目标对象什么关系？   父子关系！
 *
 * @author joeysh
 * @date 2018/08/21 00:46
 */
public class DynamicProxy {

    public static Object createProxy(final Object obj) {
        /**
         * ClassLoader :类加载器.
         * Class[]     :被增强的对象实现的所有接口
         * InvocationHandler	:处理类.
         */
        Class<?>[] interfaces = obj.getClass().getInterfaces();
        ClassLoader classLoader = obj.getClass().getClassLoader();
        /**
         * 方法的参数:
         *  proxy	:产生的代理对象.
         *  method	:当前正在调用的目标类的方法.
         *  args	:正在执行的方法中的参数.
         */
        return Proxy.newProxyInstance(classLoader, interfaces, (proxy, method, args) -> {
            System.out.println("方法执行前的增强操作。。。");
            Object invoke = method.invoke(obj, args);
            System.out.println("方法执行后的增强操作。。。");
            return invoke;
        });
    }

}
