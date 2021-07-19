package _多线程编程._安全终止线程;

import _多线程编程.utils.SleepUtils;

/**
 * @program: algorithm_by_java
 * @author: 一树
 * @data: 2020/12/5 21:34
 */
public class Demo {

    public static void main(String[] args) {
        Runner one = new Runner();
        Thread countThread = new Thread(one, "CountThread");
        countThread.start();
        //睡眠1s，main现场对CountThread进行中断，使CountThread能够感知中断而结束
        SleepUtils.second(1);
        countThread.interrupt();
        Runner two = new Runner();
        countThread = new Thread(two, "CountThread");
        countThread.start();
        //睡眠1s，main现场对CountThread进行cancel，使CountThread能够感知on=false而结束
        SleepUtils.second(1);
        two.cancel();//主线程:volatile可见性
    }


    static class Runner implements Runnable{
        private long i;
        private volatile boolean on = true;
        @Override
        public void run() {
            while (on && !Thread.currentThread().isInterrupted()){
                i++;
            }
            System.out.println("count i = " + i);
        }

        public void cancel(){
            on = false;
        }
    }
}
