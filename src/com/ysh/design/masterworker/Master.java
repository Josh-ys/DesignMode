package com.ysh.design.masterworker;

import com.ysh.design.future.utils.ThreadPoolUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * Master
 * 接收Client提交过来的任务
 * 创建Worker
 * 运行Worker
 * 汇总Task的处理结果
 *
 * @author joeysh
 * @date 2018/08/23 23:31
 */
public class Master {
    /**
     * 存放Worker的Map
     */
    private Map<String, Worker> workerMap = new HashMap<>();
    /**
     * 存放任务的队列
     */
    private ConcurrentMap<String, Object> resultMap = new ConcurrentHashMap<>();
    /**
     * 存放每一个Worker的处理结果
     */
    private BlockingQueue<Task> workQueue = new LinkedBlockingQueue<>();

    public Master(Worker worker, int workerCount) {
        worker.setResultMap(resultMap);
        worker.setWorkQueue(workQueue);
        for (int i = 0; i < workerCount; i++) {
            workerMap.put(String.valueOf(i), worker);
        }
    }

    /**
     * 需要一个提交任务,就是讲任务放入队列中
     *
     * @param task
     */
    public void submit(Task task) {
        this.workQueue.add(task);
    }

    /**
     * 执行方,启动所有的worker方法去执行任务
     */
    public void execute() {
        for (Map.Entry<String, Worker> entry : workerMap.entrySet()) {
            ThreadPoolUtil.getThreadPoll().execute(entry.getValue());
        }
    }

    /**
     * 判断是否运行结束的方法
     *
     * @return
     */
    public boolean isComplete() {
        ThreadPoolExecutor threadPool = ThreadPoolUtil.getThreadPoll().getThreadPool();
        if (threadPool.getTaskCount() == threadPool.getCompletedTaskCount()) {
            return true;
        }
        return false;
    }

    /**
     * 汇总每个Task的结果
     *
     * @return
     */
    public String getResult() {
        String result = null;
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, Object> e : resultMap.entrySet()) {
            sb.append(e.getValue()).append("-");
            sb.substring(0, sb.length() - 1);
            result = sb.toString();
        }
        return result;
    }

}
