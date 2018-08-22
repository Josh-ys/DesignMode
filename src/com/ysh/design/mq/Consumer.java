package com.ysh.design.mq;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 消息的消费者
 *
 * @author joeysh
 * @date 2018/08/23 01:01
 */
public class Consumer implements Runnable {

    /**
     * 消费者的名字
     */
    private String name;

    /**
     * 消息队列
     */
    private BlockingQueue<?> queue;

    /**
     * 随机数
     */
    private Random random = new Random();

    public Consumer(String name, BlockingQueue<?> queue) {
        this.name = name;
        this.queue = queue;
    }

    @Override
    public void run() {
        for (; ; ) {
            try {
                //超过7S无法获取数据,退出监听
                Object data = this.queue.poll(7, TimeUnit.SECONDS);
                if (data == null) {
                    System.out.println("当前消费者：" + name + ",超过7S无法获取数据,退出监听..");
                    break;
                }
                //获取到数据随机睡眠0-1000毫秒 模拟耗时
                Thread.sleep(random.nextInt(1000));
                System.out.println("当前消费者：" + name + "消费成功，消费数据为" + data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
