package com.ysh.design.future.service;

import com.ysh.design.future.dao.entity.BlogEntity;

import java.util.concurrent.ExecutionException;

/**
 * @author joeysh
 * @date 2018/08/21 23:53
 */
public interface BlogService {
    BlogEntity getArticleAndUpdateAccessNumber(Long articleId);

    BlogEntity getArticle(Long articleId);

    void updateAccessNumber(Long articleId);

    BlogEntity getArticleAndUpdateAccessNumberFuture(Long articleId) throws ExecutionException, InterruptedException, Exception;
}
