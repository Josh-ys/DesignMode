package com.ysh.design.ornamenter;

/**
 * @author joeysh
 * @date 2018/08/21 00:18
 */
public class WashDecorate extends DogDecorate {

    public WashDecorate(Dog dog) {
        super(dog);
    }

    @Override
    public void eat() {
        System.out.println("吃饭前洗手");
        super.eat();
    }
}
