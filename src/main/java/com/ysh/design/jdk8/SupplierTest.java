package com.ysh.design.jdk8;

import java.util.function.Supplier;

/**
 * @author yangshenghong
 * @date 2020-04-27
 */
public class SupplierTest {

    public static void main(String[] args) {
        String value = getSupplierValue(() -> "a");
        System.out.println(value);
    }

    /**
     * 接受lambda参数
     */
    public static <T> T getSupplierValue(Supplier<T> supplier) {
        return supplier.get();
    }

}
