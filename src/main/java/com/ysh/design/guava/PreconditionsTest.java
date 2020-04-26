package com.ysh.design.guava;

import com.google.common.base.Preconditions;

/**
 * checkArgument(boolean)
 * 检查boolean是否为true，用来检查传递给方法的参数。
 * IllegalArgumentException
 * <p>
 * <p>
 * checkNotNull(T)
 * 检查value是否为null，该方法直接返回value，因此可以内嵌使用checkNotNull。
 * NullPointerException
 * <p>
 * <p>
 * checkState(boolean)
 * 用来检查对象的某些状态。
 * IllegalStateException
 * <p>
 * <p>
 * checkElementIndex(int index, int size)
 * 检查index作为索引值对某个列表、字符串或数组是否有效。   index > 0 && index < size
 * IndexOutOfBoundsException
 * <p>
 * <p>
 * checkPositionIndexes(int start, int end, int size)
 * 检查[start,end]表示的位置范围对某个列表、字符串或数组是否有效
 * IndexOutOfBoundsException
 * <p>
 *
 * @author yangshenghong
 * @date 2020-04-26
 */
public class PreconditionsTest {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            //参数校验
            Preconditions.checkArgument(i < 5, "must be than zero:%s", i);
        }
    }
}
