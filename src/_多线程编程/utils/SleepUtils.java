package _多线程编程.utils;

import java.util.concurrent.TimeUnit;

/**
 * @program: algorithm_by_java
 * @author: 一树
 * @data: 2020/12/5 21:06
 */
public final class SleepUtils {
    public static void second(long second){
        try {
            TimeUnit.SECONDS.sleep(second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
