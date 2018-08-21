package com.ysh.design.future.service.impl;

import com.ysh.design.future.dao.BlogDao;
import com.ysh.design.future.dao.impl.BlogDaoImpl;
import com.ysh.design.future.dao.entity.BlogEntity;
import com.ysh.design.future.utils.ThreadPoolUtil;
import com.ysh.design.future.service.BlogService;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @author joeysh
 * @date 2018/08/22 00:00
 */
public class BlogServiceImpl implements BlogService {

    private BlogEntity blogEntity;

    private boolean isPrepare;

    @Override
    public BlogEntity getArticleAndUpdateAccessNumber(Long articleId) {
        // 启动一个新的线程，去加载数据
        ThreadPoolUtil.getThreadPoll().execute(() -> {
            System.out.println("启动线程去加载数据");
            getArticles(articleId);
        });
        //继续其他的处理
        this.updateAccessNumber(articleId);
        //获取真实数据并返回
        return getResultData();

    }

    private BlogEntity getResultData() {
        synchronized (this) {
            if (!isPrepare) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return blogEntity;
        }
    }

    private void getArticles(Long articleId) {
        synchronized (this) {
            if (isPrepare) {
                return;
            }
            blogEntity = getArticle(articleId);
            isPrepare = true;
            this.notify();
        }
    }

    @Override
    public BlogEntity getArticle(Long articleId) {
        System.out.println("开始获取数据");
        BlogDao blogDao = new BlogDaoImpl();
        return blogDao.getArticle(articleId);
    }

    @Override
    public void updateAccessNumber(Long articleId) {
        System.out.println("进行更新等操作");
        BlogDao blogDao = new BlogDaoImpl();
        blogDao.updateAccessNumber(articleId);
    }

    @Override
    public BlogEntity getArticleAndUpdateAccessNumberFuture(Long articleId) throws Exception {
        //构造FutureTask，并且传入需要真正进行业务逻辑处理的类,该类一定是实现了Callable接口的类
        FutureTask futureTask = new FutureTask(() -> this.getArticle(articleId));
        //这里提交任务future,则开启线程执行方法
        //submit和execute的区别： 第一点是submit可以传入实现Callable接口的实例对象， 第二点是submit方法有返回值
        ThreadPoolUtil.getThreadPoll().execute(futureTask);
        //这里可以做额外的数据操作,也就是主程序执行其他业务逻辑
        this.updateAccessNumber(articleId);
        //调用获取数据方法,如果call()方法没有执行完成,则依然会进行等待
        return (BlogEntity) futureTask.get();
    }
}
