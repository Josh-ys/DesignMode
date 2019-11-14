package com.ysh.design.masterworker;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentMap;

/**
 * Worker
 * 具体的任务处理类
 * 不断的从队列中获取任务->处理->保存结果
 *
 * @author joeysh
 * @date 2018/08/23 23:32
 */
public class Worker implements Runnable {
    private BlockingQueue<Task> workQueue;

    private ConcurrentMap<String, Object> resultMap;

    public void setWorkQueue(BlockingQueue<Task> workQueue) {
        this.workQueue = workQueue;
    }

    public void setResultMap(ConcurrentMap<String, Object> resultMap) {
        this.resultMap = resultMap;
    }

    @Override
    public void run() {
        while (true) {
            //取出一个任务队列中的任务
            Task task = this.workQueue.poll();
            if (task == null) {
                //如果没有任务了则退出
                break;
            }
            System.out.println("Worker-" + Thread.currentThread() + "-执行任务" + task);
            //开始处理任务
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            task.setWorkContent(task.getWorkContent() + 1);
            //将处理结果添加到结果集合中
            resultMap.put(task.getWorkId().toString(), task);
        }
    }
}
