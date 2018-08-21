package com.ysh.design.future.dao;

import com.ysh.design.future.dao.entity.BlogEntity;

/**
 * @author joeysh
 * @date 2018/08/21 23:53
 */
public interface BlogDao {
    BlogEntity getArticle(Long articleId);

    void updateAccessNumber(Long articleId);
}
