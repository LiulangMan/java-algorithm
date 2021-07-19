package _多线程编程._并发;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

/**
 * @program: suanfa_by_java
 * @author: 一树
 * @data: 2020/11/15 15:52
 */
public class TestApplication {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() + ":" + i);
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() + ":" + i);
                }
            }
        }).start();

    }
}
