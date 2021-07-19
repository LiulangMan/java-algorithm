package _多线程编程._Daemon线程;

import _多线程编程.utils.SleepUtils;

/**
 * @program: algorithm_by_java
 * @author: 一树
 * @data: 2020/12/5 20:58
 */
public class Daemon {
    public static void main(String[] args) {
        Thread thread = new Thread(new DaemonRunner(),"Daemon");
        thread.setDaemon(true);
        thread.start();
    }

    static class DaemonRunner implements Runnable{
        @Override
        public void run() {
            try {
                SleepUtils.second(10);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println("DaemonThread finally run");
                //守护线程不保证执行finally方法快
            }
        }
    }
}
