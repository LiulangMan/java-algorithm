package _多线程编程._暂停恢复停止;

import _多线程编程.utils.SleepUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

/**
 * @program: algorithm_by_java
 * @author: 一树
 * @data: 2020/12/5 21:22
 *
 * @Text: 不推荐使用suspend，stop，resume是因为释放资源未知，容易造成死锁；
 *        如果要安全地停止线程，就使用interrupt方法
 */
@SuppressWarnings("deprecation")
public class Demo {
    public static void main(String[] args) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Thread runner = new Thread(new Runner(), "Runner");
        runner.setDaemon(true);
        runner.start();
        SleepUtils.second(3);
        //暂停runner
        runner.suspend();
        System.out.println("main suspend runner at "+ format.format(new Date()));
        SleepUtils.second(3);
        //恢复runner
        runner.resume();
        System.out.println("main resume runner at "+ format.format(new Date()));
        SleepUtils.second(3);
        //停止runner
        runner.stop();
        System.out.println("main stop runner at "+ format.format(new Date()));
        SleepUtils.second(3);
    }

    static class Runner implements Runnable{
        @Override
        public void run() {
            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
            while (true){
                System.out.println(Thread.currentThread().getName()+"Run at"+ format.format(new Date()));
                SleepUtils.second(1);
            }
        }
    }
}
