package com.ysh.design.utils;

/**
 * 对象生成器
 *
 * @author joeysh
 * @date 2018/09/29 23:53
 */
public class BaseGenerator<T> implements Generator<T> {

    private Class<T> type;

    public BaseGenerator(Class<T> type) {
        this.type = type;
    }

    @Override
    public T next() {
        try {
            return type.newInstance();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public static <K> Generator<K> create(Class<K> type) {
        if (type == null) {
            System.out.println("params is null");
            return null;
        }
        return new BaseGenerator<K>(type);
    }

    public static void main(String[] args) {
        LinkedStack<String> stack = BaseGenerator.create(LinkedStack.class).next();
        for (String s : "a b c".split(" ")) {
            stack.add(s);
        }
        String result;
        while ((result = stack.get()) != null) {
            System.out.println(result);
        }
    }
}
