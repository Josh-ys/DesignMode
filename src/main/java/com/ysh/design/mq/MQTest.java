package com.ysh.design.mq;

import com.ysh.design.future.utils.ThreadPoolUtil;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 生产者消费者模式
 * 采用并发-阻塞队列实现
 * 方法解释:
 * ---------------------------------------------------
 * offer 如果队列已经满了,则不阻塞，不抛出异常
 * offer 可设置最大阻塞时间,2秒,如果队列还是满的,则不阻塞，不抛出异常
 * add 如果队列满了，则不阻塞，直接抛出异常
 * put 如果队列满了,则永远阻塞, 不抛出异常
 * ---------------------------------------------------
 * peek 读取头元素不移除，队列为空,返回null,不阻塞, 不抛异常
 * poll 读取头元素并移除，队列为空,返回null,不阻塞, 不抛异常
 * poll 可指定阻塞时间,2秒,如果队列依然为空,则返回null,不抛异常
 * take 读取头元素并移除,如果队列为空,则永远阻塞,不抛出异常
 * drainTo 取出queue中指定个数（或全部）的元素放入list中,并移除，当队列为空时不抛出异常
 *
 * @author joeysh
 * @date 2018/08/23 01:20
 */
public class MQTest {
    public static void main(String[] args) {

        /**
         * BlockingQueue的实现类之LinkedBlockingDeque
         * 基于链表的阻塞队列,内部维护一个链表存储缓存数据
         * 内部采用读写分离的锁机制,所以支持写入和读取的并发操作
         * 创建时可指定长度也可以不指定，不指定时代表无界队列
         * 不允许null值
         */
        BlockingQueue<MailContent> contents = new LinkedBlockingQueue<>();

        //生产者
        Producer producer = new Producer("生产者一号", contents);
        Producer producer2 = new Producer("生产者二号", contents);
        Producer producer3 = new Producer("生产者三号", contents);
        Producer producer4 = new Producer("生产者四号", contents);
        Producer producer5 = new Producer("生产者五号", contents);

        //消费者
        Consumer consumer = new Consumer("消费者一号", contents);
        Consumer consumer2 = new Consumer("消费者二号", contents);
        Consumer consumer3 = new Consumer("消费者三号", contents);
        Consumer consumer4 = new Consumer("消费者四号", contents);
        Consumer consumer5 = new Consumer("消费者五号", contents);
        Consumer consumer6 = new Consumer("消费者六号", contents);

        ThreadPoolUtil threadPoll = ThreadPoolUtil.getThreadPoll();
        threadPoll.execute(producer);
        threadPoll.execute(producer2);
        threadPoll.execute(producer3);
        threadPoll.execute(producer4);
        threadPoll.execute(producer5);
        threadPoll.execute(consumer);
        threadPoll.execute(consumer2);
        threadPoll.execute(consumer3);
        threadPoll.execute(consumer4);
        threadPoll.execute(consumer5);
        threadPoll.execute(consumer6);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //生产者停止产生数据
        producer.stop();
        producer2.stop();
        producer3.stop();
        producer4.stop();
        producer5.stop();
    }
}
