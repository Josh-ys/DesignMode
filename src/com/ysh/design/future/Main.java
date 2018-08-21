package com.ysh.design.future;

import com.ysh.design.future.controller.BlogController;
import com.ysh.design.future.dao.entity.BlogEntity;

/**
 * 测试验证
 *
 * @author joeysh
 * @date 2018/08/22 01:15
 */
public class Main {
    public static void main(String[] args) {
        BlogController blogController = new BlogController();
        BlogEntity entity = blogController.getArticleAndUpdateAccessNumberFuture(1L);
        System.out.println(entity);
    }
}
