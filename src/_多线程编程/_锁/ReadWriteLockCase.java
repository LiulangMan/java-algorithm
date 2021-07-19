package _多线程编程._锁;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @program: algorithm_by_java
 * @author: 一树
 * @data: 2020/12/9 14:00
 * @Text: 在读大于写的情况下，读写锁吞吐量远大于排它锁
 */
public class ReadWriteLockCase {
    private static Map<String, Object> map = new HashMap<>();
    private static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    static Lock r = rwl.readLock();//读锁
    static Lock w = rwl.writeLock();//写锁

    public static Object get(String key) {
        r.lock();//获取写锁
        try {
            return map.get(key);
        } finally {
            r.unlock();
        }
    }

    public static Object put(String key, Object object) {
        w.lock();//获取写锁
        try {
            return map.put(key, object);
        } finally {
            w.unlock();
        }
    }

    //清空所有内容
    public static void clear() {
        w.lock();
        try {
            map.clear();
        } finally {
            w.unlock();
        }
    }
}
