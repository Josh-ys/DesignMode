package com.ysh.design.mq;

import com.ysh.design.future.utils.ThreadPoolUtil;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author joeysh
 * @date 2018/08/23 01:20
 */
public class MQTest {
    public static void main(String[] args) {
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
