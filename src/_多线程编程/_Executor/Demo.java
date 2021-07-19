package _多线程编程._Executor;

import _多线程编程.utils.SleepUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: algorithm_by_java
 * @author: 一树
 * @data: 2020/12/6 14:26
 * @Text: Executor框架
 */
public class Demo {
    public static void readMe() {
        //FixedThreadPool被称为可重用固定线程数的线程池
        //其corePoolSize和maximumPoolSize都被设置为创建其指定参数的nThreads
        //由于调用无界工作队列，拒绝策略无效
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
        //singleThreadExecutor的corePoolSize和maximumPoolSize都为1，等效Executors.newFixedThreadPool(1);
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        //cachedThreadPool的corePoolSize为0，maximumPoolSize为Integer.MAX_VALUE，采用无容量的SynchronousQueue
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    }

    public static void main(String[] args) {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        cachedThreadPool.submit(new Thread(new CountThread()), "线程1");
        cachedThreadPool.submit(new Thread(new CountThread()), "线程2");
        SleepUtils.second(3);
        System.out.println("------main-------");
        cachedThreadPool.shutdown();
    }

    static class CountThread implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + ": i = " + i);
                SleepUtils.second(1);
            }
        }
    }
}
