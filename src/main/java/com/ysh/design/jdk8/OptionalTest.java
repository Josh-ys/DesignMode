package com.ysh.design.jdk8;

import java.util.Optional;

/**
 * @author yangshenghong
 * @date 2020-04-27
 */
public class OptionalTest {
    public static void main(String[] args) {
        String str = "test";
        //创建对象
        Optional<String> fullName = Optional.ofNullable(str);
        //是否为空
        System.out.println("Full Name is set? " + fullName.isPresent());
        //为空输出test2 与下面的区别是 这里可以执行指定逻辑
        System.out.println(fullName.orElseGet(() -> "test2"));
        //为空输出[none]
        System.out.println("Full Name: " + fullName.orElse("[none]"));
        System.out.println(fullName.map(s -> "Hey " + s + "!").orElse("Hey Stranger!"));


        Optional<String> firstName = Optional.of("Tom");
        System.out.println("First Name is set? " + firstName.isPresent());
        System.out.println("First Name: " + firstName.orElseGet(() -> "[none]"));
        System.out.println(firstName.map(s -> "Hey " + s + "!").orElse("Hey Stranger!"));

        //值存在 做指定的事情
        firstName.ifPresent(System.out::println);
    }
}
