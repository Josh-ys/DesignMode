package com.ysh.design.builder;
import static com.ysh.design.builder.User.createUser;
/**
 * @author joeysh
 * @date 2018/08/20 23:54
 */
public class UserTest {
    public static void main(String[] args) {
        User user = createUser().setName("angel").setAge(20).builderUser();
        System.out.println(user);
    }
}
