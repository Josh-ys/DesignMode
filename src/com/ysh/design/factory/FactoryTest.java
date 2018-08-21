package com.ysh.design.factory;

/**
 * @author joeysh
 * @date 2018/08/20 23:45
 */
public class FactoryTest {
    public static void main(String[] args) {
        AnimalFactory factory = new DogFactory();
        Animal animal = factory.createAnimal();
        animal.eat();

        //****************************************

        factory = new CatFactory();
        animal = factory.createAnimal();
        animal.eat();

    }
}
