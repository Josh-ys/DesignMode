package com.ysh.design.ornamenter;

/**
 * 装饰者的实现的前提：
 * 1.装饰的类和被装饰的类必须实现相同的接口
 * 2.装饰的类必须引入被装饰者的类的引用
 *
 * @author joeysh
 * @date 2018/08/21 00:09
 */
public interface Animal {
    /**
     * 共同的接口
     */
    void eat();
}
