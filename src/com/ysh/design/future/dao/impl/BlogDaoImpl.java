package com.ysh.design.future.dao.impl;

import com.ysh.design.future.dao.entity.BlogEntity;
import com.ysh.design.future.dao.BlogDao;

/**
 * @author joeysh
 * @date 2018/08/22 00:45
 */
public class BlogDaoImpl implements BlogDao {
    @Override
    public BlogEntity getArticle(Long articleId) {
        BlogEntity entity = new BlogEntity();
        entity.setId(articleId);
        entity.setTitle("程序员的职业素养");
        entity.setContent("内容...");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("获取数据完成...");
        return entity;
    }

    @Override
    public void updateAccessNumber(Long articleId) {
        try {
            Thread.sleep(2000);
            System.out.println("更新操作完成...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
