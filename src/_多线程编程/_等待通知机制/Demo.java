package _多线程编程._等待通知机制;

import _多线程编程.utils.SleepUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: algorithm_by_java
 * @author: 一树
 * @data: 2020/12/6 13:02
 * @Text: 通过Object内置的wait() notify() notifyAll()方法
 */
public class Demo {
    private static boolean flag = true;
    private static final Object lock = new Object();

    public static void main(String[] args) {
        Thread waitThread = new Thread(new Wait(), "WaitThread");
        Thread notifyThread = new Thread(new Notify(), "NotifyThread");
        waitThread.start();
        SleepUtils.second(1);
        notifyThread.start();
    }


    static class Wait implements Runnable {

        @Override
        public void run() {
            //加锁
            synchronized (lock) {
                //当条件不满足时，继续wait，同时释放lock锁
                while (flag) {
                    try {
                        System.out.println(Thread.currentThread() + "flag is true.wait @" +
                                new SimpleDateFormat("HH:mm:ss").format(new Date()));
                        lock.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                //条件满足时，完成工作
                System.out.println(Thread.currentThread() + "flag is false.running @" +
                        new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
        }
    }

    static class Notify implements Runnable {

        @Override
        public void run() {
            //加锁，拥有lock的Monitor
            synchronized (lock) {
                //获取lock的锁，然后进行通知，通知时不会释放lock的锁
                //直到当前线程释放了lock后，WaitThread才能从wait方法中返回
                System.out.println(Thread.currentThread() + "hold lock.notify @" +
                        new SimpleDateFormat("HH:mm:ss").format(new Date()));
                lock.notifyAll();
                flag = false;
                SleepUtils.second(5);
            }
            //再次加锁(抢占)
            synchronized (lock) {
                System.out.println(Thread.currentThread() + "hold lock again.sleep @" +
                        new SimpleDateFormat("HH:mm:ss").format(new Date()));
                SleepUtils.second(5);
            }
        }
    }
}
