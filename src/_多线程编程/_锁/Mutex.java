package _多线程编程._锁;

import _多线程编程.utils.SleepUtils;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @program: algorithm_by_java
 * @author: 一树
 * @data: 2020/12/8 13:56
 * @Text: 自定义独占锁Mutex
 */
public class Mutex implements Lock {

    private static class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected boolean tryAcquire(int arg) {
            //当状态为0时获取锁
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            //释放锁，状态设置为0
            if (super.getState() == 0) {
                throw new IllegalMonitorStateException();
            }
            super.setExclusiveOwnerThread(null);
            super.setState(0);
            return true;
        }

        @Override
        protected boolean isHeldExclusively() {
            return super.getState() == 1;
        }

        //返回一个condition，每个condition都包含了一个condition队列
        Condition newCondition() {
            return new ConditionObject();
        }
    }

    //仅需要将操作代理到sync上即可
    private final Sync sync = new Sync();

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }

    public boolean isLocked() {
        return sync.isHeldExclusively();
    }

    public boolean hasQueuedThreads() {
        return sync.hasQueuedThreads();
    }

    //test
    public static void main(String[] args) {
        Lock lock = new Mutex();
        lock.lock();
        try {
            System.out.println("lock");
            SleepUtils.second(1);
        }finally {
            lock.unlock();
        }
    }
}
