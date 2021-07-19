package _多线程编程._锁;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: algorithm_by_java
 * @author: 一树
 * @data: 2020/12/9 14:18
 */
public class Condition {
    private static Lock lock = new ReentrantLock();
    private static java.util.concurrent.locks.Condition condition = lock.newCondition();

    public static void main(String[] args) {

    }


    private static class CountRunning implements Runnable {
        private Lock lock;
        private java.util.concurrent.locks.Condition condition;

        public CountRunning(Lock lock, java.util.concurrent.locks.Condition condition) {
            this.lock = lock;
            this.condition = condition;
        }


        @Override
        public void run() {
            lock.lock();
            try {
                condition.await();//condition提供了类似Object的wait notify notifyAll方法
                //condition.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
