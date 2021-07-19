package _多线程编程._理解中断;

import _多线程编程.utils.SleepUtils;

/**
 * @program: algorithm_by_java
 * @author: 一树
 * @data: 2020/12/5 21:12
 */
public class Interrupt {

    public static void main(String[] args) {
        //sleepThread 不断尝试睡眠
        Thread sleepRunner = new Thread(new SleepRunner(), "sleepRunner");
        sleepRunner.setDaemon(true);

        //busyThread 忙碌
        Thread busyRunner = new Thread(new BusyRunner(), "busyRunner");
        busyRunner.setDaemon(true);

        sleepRunner.start();
        busyRunner.start();

        //休息5s，让sleep和busy充分运行
        SleepUtils.second(5);

        sleepRunner.interrupt();
        busyRunner.interrupt();

        System.out.println("sleepThread interrupt is "+sleepRunner.isInterrupted());
        System.out.println("busyThread interrupt is "+busyRunner.isInterrupted());
        SleepUtils.second(5);
    }

    static class SleepRunner implements Runnable {
        //在抛出interruptException时，会清除isInterrupt标志
        @Override
        public void run() {
            while (true) {
                SleepUtils.second(10);
            }
        }
    }


    static class BusyRunner implements Runnable {

        @Override
        public void run() {
            while (true){

            }
        }
    }
}
