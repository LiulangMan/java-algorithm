package _多线程编程._线程池实例;

import _多线程编程.utils.SleepUtils;

/**
 * @program: algorithm_by_java
 * @author: 一树
 * @data: 2020/12/7 13:18
 */
public class Client {
    public static void main(String[] args) {
        ThreadPool<CountThread> threadPool = new DefaultThreadPool<>();
        threadPool.execute(new CountThread());
        threadPool.execute(new CountThread());
        threadPool.execute(new CountThread());

        SleepUtils.second(6);
        System.out.println("--------shutdown-----------");
        threadPool.shutdown();
    }

    static class CountThread implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "  i = " + i);
                SleepUtils.second(1);
            }
        }
    }
}
