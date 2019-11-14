package com.ysh.design.template;

/**
 * 完成一件事情，有固定的数个步骤，但是每个步骤根据对象的不同，而实现细节不同；
 * 就可以在父类中定义一个完成该事情的总方法，按照完成事件需要的步骤去调用其每个步骤的实现方法。
 * 每个步骤的具体实现，由子类完成。
 * <p>
 * 定义骨架
 *
 * @author joeysh
 * @date 2018/08/21 01:31
 */
public abstract class Template {
    public void sleep() {
        this.first();
        this.second();
        this.thirdly();
    }

    /**
     * 第一步
     */
    protected abstract void first();

    /**
     * 第二步
     */
    protected abstract void second();

    /**
     * 第三步
     */
    protected abstract void thirdly();
}
