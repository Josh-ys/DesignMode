package com.ysh.design.masterworker;

import java.util.Random;

/**
 * Master-Worker模式是一种将串行任务并行化的方案，被分解的子任务在
 * 系统中可以被并行处理，同时，如果有需要，Master进程不需要等待所
 * 有子任务都完成计算，就可以根据已有的部分结果集计算最终结果集。
 * 客户端将所有任务提交给Master，Master分配Worker去并发处理任务，并
 * 将每一个任务的处理结果返回给Master，所有的任务处理完毕后,由
 * Master进行结果汇总再返回给Client
 *
 * @author joeysh
 * @date 2018/08/23 23:32
 */
public class MasterWorkerTest {
    public static void main(String[] args) {
        //创建多个Worker
        Master master = new Master(new Worker(), 12);
        Random r = new Random();
        for (int i = 1; i <= 12; i++) {
            Task t = new Task();
            t.setWorkId(i);
            t.setWorkContent(r.nextInt(1000) + "");
            master.submit(t);
        }
        //master启动所有的worker
        master.execute();
        long start = System.currentTimeMillis();
        //检测所有的worker是否都执行完毕
        while (true) {
            if (master.isComplete()) {
                long end = System.currentTimeMillis() - start;
                String result = master.getResult();
                System.err.println("结果：" + result + "---执行时间:" + end);
                break;
            }
        }
    }
}
