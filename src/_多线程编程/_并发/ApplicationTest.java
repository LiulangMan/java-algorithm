package _多线程编程._并发;

import _多线程编程.utils.SleepUtils;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: algorithm_by_java
 * @author: 一树
 * @data: 2020/11/25 21:27
 */
public class ApplicationTest {
    private static int goods = 100;
    private static final Object lock = new Object();
    private static Lock lock1 = new ReentrantLock();

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {

                while (goods > 0) {
                    synchronized (lock) {
                        System.out.println(Thread.currentThread().getName() + ":" + (--goods));
                    }
                    try {
                        SleepUtils.second(10);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (goods > 0) {
                    synchronized (lock){
                        System.out.println(Thread.currentThread().getName() + ":" + (--goods));
                    }
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        t1.start();
        t2.start();


    }
}
