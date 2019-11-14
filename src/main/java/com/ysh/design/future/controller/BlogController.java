package com.ysh.design.future.controller;

import com.ysh.design.future.dao.entity.BlogEntity;
import com.ysh.design.future.service.BlogService;
import com.ysh.design.future.service.impl.BlogServiceImpl;

/**
 * 简单来说,客户端请求之后，先返回一个应答结果，然后异步的去准
 * 备数据，客户端可以先去处理其他事情，当需要最终结果的时候再来
 * 获取, 如果此时数据已经准备好，则将真实数据返回；如果此时数据
 * 还没有准备好，则阻塞等待。
 *
 * @author joeysh
 * @date 2018/08/21 23:52
 */
public class BlogController {
    /**
     * 模拟用户用户查询阅读一篇文章，假设查询文章耗时较长。
     * 同时会去更新该文章的访问量等其它操作
     *
     * @param articleId
     * @return
     */
    public BlogEntity getArticleAndUpdateAccessNumber(Long articleId) {
        BlogService blogService = new BlogServiceImpl();
        return blogService.getArticleAndUpdateAccessNumber(articleId);
    }

    /**
     * 使用jdk的future可以完成上面方法的效果
     *
     * @param articleId
     * @return
     */
    public BlogEntity getArticleAndUpdateAccessNumberFuture(Long articleId) {
        BlogEntity blogEntity = null;
        try {
            BlogService blogService = new BlogServiceImpl();
            blogEntity = blogService.getArticleAndUpdateAccessNumberFuture(articleId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return blogEntity;
    }
}
