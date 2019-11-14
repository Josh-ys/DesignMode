package com.ysh.design.builder;

/**
 * @author joeysh
 * @date 2018/08/20 23:48
 */
public class User {
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public static Builder createUser() {
        return new Builder();
    }

    public static class Builder {
        private String name;
        private Integer age;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setAge(Integer age) {
            this.age = age;
            return this;
        }

        public User builderUser() {
            User user = new User();
            user.setName(this.name);
            user.setAge(this.age);
            return user;
        }
    }
}
