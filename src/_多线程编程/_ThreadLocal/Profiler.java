package _多线程编程._ThreadLocal;

import _多线程编程.utils.SleepUtils;

/**
 * @program: algorithm_by_java
 * @author: 一树
 * @data: 2020/12/6 13:46
 *
 * @Text: ThreadLocal 线程变量,类似Map<Thread,T>，将某个值捆绑到当前线程上，通过set(T),get()方法
 */
public class Profiler {
    private static final ThreadLocal<Long> TIME_THREAD_LOCAL = ThreadLocal.withInitial(System::currentTimeMillis);

    public static void begin() {
        TIME_THREAD_LOCAL.set(System.currentTimeMillis());
    }

    public static long end() {
        return System.currentTimeMillis() - TIME_THREAD_LOCAL.get();
    }

    public static void main(String[] args) {
        Profiler.begin();
        SleepUtils.second(2);
        System.out.println("Cost " + Profiler.end() + " mills");
    }
}
