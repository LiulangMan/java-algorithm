package _多线程编程._锁;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: algorithm_by_java
 * @author: 一树
 * @data: 2020/12/8 13:49
 *
 * @Text: Lock
 */
public class LockUseCase {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            //do something
            //此处不能lock.lock()
        }finally {
            lock.unlock();
        }
    }


    public static void readMe() throws InterruptedException {
        Lock lock = new ReentrantLock();//可重入锁
        lock.lockInterruptibly();//可中断地获取锁
        lock.tryLock();//尝试非阻塞地获取锁
        lock.tryLock(1, TimeUnit.SECONDS);//超时的获取锁,成功返回true
        lock.unlock();//释放锁
    }
}
