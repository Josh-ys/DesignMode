package com.ysh.design.future.utils;

import java.util.concurrent.*;

/**
 * @author joeysh
 * @date 2018/08/22 00:06
 */
public class ThreadPoolUtil {
    private static int coreThread;
    private static int MaxThread;
    private static final long KEEP_ALIVE_TIME = 1;

    private static ThreadPoolUtil threadPoolUtil;

    private ThreadPoolExecutor executorService;

    public static ThreadPoolUtil getThreadPoll() {
        synchronized (ThreadPoolUtil.class) {
            if (threadPoolUtil == null) {
                // 获取处理器数量
                int cpuNum = Runtime.getRuntime().availableProcessors();
                // 根据cpu数量,计算出合理的线程并发数
                coreThread = cpuNum * 2;
                MaxThread = coreThread + 1;
                threadPoolUtil = new ThreadPoolUtil();
            }
        }
        return threadPoolUtil;
    }

    public void execute(Runnable runnable) {
        executorService = new ThreadPoolExecutor(
                coreThread, MaxThread, KEEP_ALIVE_TIME, TimeUnit.MICROSECONDS, new LinkedBlockingQueue<>(1),
                new ThreadPoolExecutor.DiscardPolicy());
        executorService.execute(runnable);
        executorService.allowCoreThreadTimeOut(true);
    }

    public void shutdown() {
        if (executorService != null) {
            executorService.shutdown();
            executorService = null;
        }
    }

    /**
     * 从线程队列中移除对象
     */
    public void cancel(Runnable runnable) {
        if (executorService != null) {
            executorService.getQueue().remove(runnable);
        }
    }
}
