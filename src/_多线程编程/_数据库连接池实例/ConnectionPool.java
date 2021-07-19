package _多线程编程._数据库连接池实例;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * @program: algorithm_by_java
 * @author: 一树
 * @data: 2020/12/6 21:29
 */
public class ConnectionPool {
    private LinkedList<Connection> pool = new LinkedList<>();

    ConnectionPool(int initSize) {
        if (initSize > 0) {
            for (int i = 0; i < initSize; i++) {
                pool.add(ConnectionDriver.createConnection());
            }
        }
    }

    void releaseConnection(Connection connection) {
        if (connection == null) return;

        synchronized (pool) {
            //连接释放后要进行通知，这样其它线程可以感知到连接池中已经归还了一个连接
            pool.addLast(connection);
            pool.notifyAll();
        }
    }

    //设置超时实际mills，返回null
    //负数表示无限等待
    Connection fetchConnection(long mills) throws InterruptedException {
        synchronized (pool) {
            if (mills <= 0) {
                while (pool.isEmpty()) {
                    pool.wait();
                }
                return pool.removeFirst();//获取第一个连接
            } else {
                //超时模式标准写法
                long future = System.currentTimeMillis() + mills;
                long remaining = mills;
                while (pool.isEmpty() && remaining > 0) {
                    pool.wait(remaining);
                    remaining = future - System.currentTimeMillis();
                }
                Connection result = null;
                if (!pool.isEmpty()) {
                    result = pool.removeFirst();
                }
                return result;
            }
        }
    }
}
