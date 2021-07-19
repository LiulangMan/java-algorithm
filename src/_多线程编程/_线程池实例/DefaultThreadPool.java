package _多线程编程._线程池实例;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @program: algorithm_by_java
 * @author: 一树
 * @data: 2020/12/7 12:52
 */
public class DefaultThreadPool<Job extends Runnable> implements ThreadPool<Job> {
    //线程池最大限制
    private static final int MAX_WORKER_NUMBER = 10;

    //线程池默认数量
    private static final int DEFAULT_WORKER_NUMBER = 5;

    //线程池最小数量
    private static final int MIN_WORKER_NUMBER = 1;

    //工作列表
    private final LinkedList<Job> jobs = new LinkedList<>();

    //工作者列表
    private final List<Worker> workers = new LinkedList<>();

    //工作者线程数量
    private int workerNum = DEFAULT_WORKER_NUMBER;

    //线程编号生成
    private AtomicLong threadNum = new AtomicLong();

    public DefaultThreadPool() {
        initializeWorkers(DEFAULT_WORKER_NUMBER);
    }

    public DefaultThreadPool(int num) {
        workerNum = Math.max(Math.min(num, MAX_WORKER_NUMBER), MIN_WORKER_NUMBER);
        initializeWorkers(workerNum);
    }

    private void initializeWorkers(int defaultWorkerNumber) {
        for (int i = 0; i < defaultWorkerNumber; i++) {
            Worker worker = new Worker();
            workers.add(worker);
            Thread thread = new Thread(worker, "ThreadPool-Worker-" + threadNum.incrementAndGet());
            thread.start();
        }
    }


    @Override
    public void execute(Job job) {
        if (job != null) {
            synchronized (jobs) {
                //添加一个工作，然后进行通知
                jobs.addLast(job);
                jobs.notify();
            }
        }

    }

    @Override
    public void shutdown() {
        for (Worker worker : workers) {
            worker.shutdown();
        }
    }

    @Override
    public void addWorker(int num) {
        synchronized (jobs) {
            //限制超过最大值
            if (num + this.workerNum > MAX_WORKER_NUMBER) {
                num = MAX_WORKER_NUMBER - this.workerNum;
            }
            initializeWorkers(num);
            this.workerNum += num;
        }
    }

    @Override
    public void removeWorker(int num) {
        synchronized (jobs) {
            if (num >= this.workerNum) {
                throw new IllegalArgumentException("beyond workNum!");
            }
            //按照给定的数量停止worker
            int count = 0;
            while (count < num) {
                Worker worker = workers.get(count);
                if (workers.remove(worker)) {
                    worker.shutdown();
                    count++;
                }
            }
            this.workerNum -= count;
        }
    }

    @Override
    public int getJobSize() {
        return jobs.size();
    }


    private class Worker implements Runnable {
        //是否工作
        private volatile boolean running = true;

        @Override
        public void run() {
            while (running) {
                Job job = null;
                synchronized (jobs) {
                    //如果工作者列表是空的，那么就wait
                    while (jobs.isEmpty()) {
                        try {
                            jobs.wait();
                        } catch (InterruptedException e) {
                            //感知到外部对workerThread的中断操作，返回
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    //取出一个job
                    job = jobs.removeFirst();
                }
                if (job != null) {
                    try {
                        job.run();
                    } catch (Exception e) {
                        //忽略job中的异常
                    }
                }

            }
        }

        void shutdown() {
            running = false;
        }
    }
}
