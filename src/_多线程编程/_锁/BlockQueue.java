package _多线程编程._锁;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @program: algorithm_by_java
 * @author: 一树
 * @data: 2020/12/9 14:26
 * @Text: 使用Condition方法实现阻塞队列
 */
public class BlockQueue<T> {
    private final Object[] items;
    private final Lock lock = new ReentrantLock();
    private final Condition notEmpty = lock.newCondition();
    private final Condition notFull = lock.newCondition();

    private int addIndex, removeIndex, count;

    BlockQueue(int capacity) {
        this.items = new Object[capacity];
    }

    //添加一个元素，如果队列已满，阻塞，直到有空位
    public void add(T t) {
        lock.lock();
        try {
            while (count == items.length) {
                notFull.await();
            }

            items[addIndex] = t;
            if (++addIndex == items.length) {
                addIndex = 0;
            }
            ++count;
            notEmpty.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    //移除一个元素，如果队列为空，阻塞
    @SuppressWarnings("unchecked")
    public T remove() {
        lock.lock();
        try {
            while (count == 0) {
                notEmpty.await();
            }
            Object item = items[removeIndex];
            if (++removeIndex == items.length) {
                removeIndex = 0;
            }
            --count;
            notFull.signal();
            return (T) item;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } finally {
            lock.unlock();
        }
    }
}
