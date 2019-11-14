package com.ysh.design.ornamenter;

/**
 * @author joeysh
 * @date 2018/08/21 00:15
 */
public class MusicDecorate extends DogDecorate {

    public MusicDecorate(Dog dog) {
        super(dog);
    }

    @Override
    public void eat() {
        System.out.println("吃饭的时候先放音乐");
        super.eat();
    }
}
