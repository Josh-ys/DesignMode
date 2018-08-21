package com.ysh.design.ornamenter;

/**
 * 装饰者
 *
 * @author joeysh
 * @date 2018/08/21 00:11
 */
public class DogDecorate implements Animal {
    private Dog dog;

    public DogDecorate(Dog dog) {
        this.dog = dog;
    }

    @Override
    public void eat() {
        this.dog.eat();
    }
}
