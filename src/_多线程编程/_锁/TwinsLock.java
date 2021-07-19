package _多线程编程._锁;

import _多线程编程.utils.SleepUtils;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @program: algorithm_by_java
 * @author: 一树
 * @data: 2020/12/8 22:15
 * @Text: 加深对同步器的理解，自定义一个：该工具在同一时刻，只允许至多两个线程访问,超过两个线程将被阻塞
 */
public class TwinsLock implements Lock {

    //count 表示可获得同步状态的最大线程数
    private static Sync sync = new Sync(2);

    private static class Sync extends AbstractQueuedSynchronizer {

        Sync(int count) {
            if (count <= 0) {
                throw new IllegalArgumentException("count must large than zero!!!");
            }
            setState(count);
        }

        @Override
        protected int tryAcquireShared(int reduceCount) {
            while (true) {
                int current = getState();
                int newCount = current - reduceCount;
                if (newCount < 0 || compareAndSetState(current, newCount)) {
                    return newCount;
                }
            }
        }

        @Override
        protected boolean tryReleaseShared(int returnCount) {
            while (true) {
                int current = getState();
                int newCount = current + returnCount;
                if (compareAndSetState(current, newCount)) {
                    return true;
                }
            }
        }
    }

    @Override
    public void lock() {
        sync.acquireShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        sync.releaseShared(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    //test
    public static void main(String[] args) {
        Lock lock = new TwinsLock();
        Thread thread1 = new Thread(new CountRunning(lock), "countThread-1");
        Thread thread2 = new Thread(new CountRunning(lock), "countThread-2");
        Thread thread3 = new Thread(new CountRunning(lock), "countThread-3");
        Thread thread4 = new Thread(new CountRunning(lock), "countThread-4");
        Thread thread5 = new Thread(new CountRunning(lock), "countThread-5");
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();

    }

    private static class CountRunning implements Runnable {

        private Lock lock;

        public CountRunning(Lock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            lock.lock();
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() + " i = " + i);
                    SleepUtils.second(1);
                }
            }finally {
                lock.unlock();
            }
        }
    }
}
