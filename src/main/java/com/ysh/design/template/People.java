package com.ysh.design.template;

/**
 * @author joeysh
 * @date 2018/08/21 01:35
 */
public class People extends Template {
    @Override
    public void first() {
        System.out.println("洗漱");
    }

    @Override
    public void second() {
        System.out.println("玩手机");
    }

    @Override
    public void thirdly() {
        System.out.println("闭眼");
    }
}
