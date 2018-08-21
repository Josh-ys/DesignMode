package com.ysh.design.factory;

/**
 * @author joeysh
 * @date 2018/08/20 23:44
 */
public class DogFactory implements AnimalFactory {
    /**
     * 创建实例
     *
     * @return
     */
    @Override
    public Animal createAnimal() {
        return new Dog();
    }
}
