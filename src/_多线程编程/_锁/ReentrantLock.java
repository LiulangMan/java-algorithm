package _多线程编程._锁;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @program: algorithm_by_java
 * @author: 一树
 * @data: 2020/12/9 12:47
 * @Text: 可重入锁的简单实现
 */
public class ReentrantLock implements Lock {

    private Sync sync = new Sync();

    private static class Sync extends AbstractQueuedSynchronizer {

        public Sync() {
            setState(0);
        }

        @Override
        protected boolean tryAcquire(int arg) {
            Thread current = Thread.currentThread();
            int state = getState();
            if (state == 0) {
                if (compareAndSetState(0, arg)) {
                    setExclusiveOwnerThread(current);
                    return true;
                }
            } else if (current == getExclusiveOwnerThread()) {
                //可重入，继续判断
                int nextState = state + arg;
                if (nextState < 0) {
                    throw new Error("Maximum lock count exceeded");
                }
                setState(nextState);
                return true;
            }

            return false;

        }

        @Override
        protected boolean tryRelease(int arg) {
            int c = getState() - arg;
            if (Thread.currentThread() != getExclusiveOwnerThread()) {
                throw new IllegalMonitorStateException();
            }
            boolean free = false;
            if (c == 0) {
                free = true;
                setExclusiveOwnerThread(null);
            }
            setState(c);
            //当可重入为0时，才可释放锁
            return free;
        }
    }

    @Override
    public void lock() {
        sync.acquire(1);
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
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
