package com.ysh.design.ornamenter;

/**
 * @author joeysh
 * @date 2018/08/21 00:41
 */
public class AnimalTest {
    public static void main(String[] args) {
        Dog dog = new Dog();
        DogDecorate dogDecorate = new MusicDecorate(dog);
        dogDecorate.eat();

        //************************

        dogDecorate = new WashDecorate(dog);
        dogDecorate.eat();
    }
}
