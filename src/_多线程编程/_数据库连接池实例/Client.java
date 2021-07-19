package _多线程编程._数据库连接池实例;

import java.sql.Connection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: algorithm_by_java
 * @author: 一树
 * @data: 2020/12/6 21:45
 */
public class Client {
    private static ConnectionPool pool = new ConnectionPool(5);
    //保证所有ConnectionRunner能够同时开始
    private static CountDownLatch start = new CountDownLatch(1);
    //main线程在所有ConnectionRunner同时结束后才可继续执行
    private static CountDownLatch end;

    public static void main(String[] args) throws InterruptedException {
        //线程数量，可以修改线程数量进行观察
        int threadCount = 10;
        end = new CountDownLatch(threadCount);
        int count = 20;
        AtomicInteger got = new AtomicInteger();
        AtomicInteger notGot = new AtomicInteger();
        for (int i = 0; i < threadCount; i++) {
            Thread runnerThread = new Thread(new ConnectionRunner(count, got, notGot), "ConnectionRunnerThread");
            runnerThread.start();
        }
        start.countDown();//减1:单线程，原子操作
        end.await();//阻塞
        System.out.println("total invoke:" + (threadCount * count));
        System.out.println("got connection:" + got);
        System.out.println("notGot connection:" + notGot);

    }


    static class ConnectionRunner implements Runnable {

        int count;
        AtomicInteger got;
        AtomicInteger notGot;

        ConnectionRunner(int count, AtomicInteger got, AtomicInteger notGot) {
            this.count = count;
            this.got = got;
            this.notGot = notGot;
        }

        @Override
        public void run() {
            try {
                //计数器到0后，才可继续执行await()后面的代码
                start.await();
            } catch (Exception e) {
                e.printStackTrace();
            }

            while (count > 0) {
                try {
                    //从线程池中获取连接，如果超时，返回null
                    //分别统计连接获取的数量got和未获取到的数量notGot
                    Connection connection = pool.fetchConnection(1000);
                    if (connection != null) {
                        try {
                            connection.createStatement();
                            connection.commit();
                        } finally {
                            pool.releaseConnection(connection);
                            got.incrementAndGet();
                        }
                    } else {
                        notGot.incrementAndGet();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    count--;
                }
            }
            //当所有线程执行完后，才能统计got 和 notGot
            end.countDown();
        }
    }

}
