package com.ysh.design.mq;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author joeysh
 * @date 2018/08/23 01:01
 */
public class Producer implements Runnable {
    /**
     * 生产者的名字
     */
    private String name;

    /**
     * 消息队列
     */
    private BlockingQueue<MailContent> queue;

    /**
     * 即时返回线程的状态
     */
    private volatile boolean isRunning = true;

    /**
     * 数据id生成器
     */
    private static AtomicInteger count = new AtomicInteger();

    /**
     * 随机数
     */
    private static Random random = new Random();

    public Producer(String name, BlockingQueue<MailContent> queue) {
        this.name = name;
        this.queue = queue;
    }

    @Override
    public void run() {
        while (isRunning) {
            try {
                //随机休眠0 - 1000 毫秒 表示创建数据的耗时
                Thread.sleep(random.nextInt(1000));
                //生成数据的ID
                int id = count.incrementAndGet();
                //创建数据对象
                MailContent mailContent = new MailContent(id, "标题" + id, "内容" + id);
                if (!this.queue.offer(mailContent, 2, TimeUnit.SECONDS)) {
                    System.out.println("生产者" + name + "将" + mailContent + "放入队列中超时...");
                } else {
                    System.out.println("生产者" + name + ", 创建了数据为:" + mailContent + ",并放入消息队列中");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        isRunning = false;
    }
}
