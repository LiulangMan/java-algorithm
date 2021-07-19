package _多线程编程._原子类;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @program: algorithm_by_java
 * @author: 一树
 * @data: 2020/12/9 20:57
 */
public class AtomicTest {
    private static void readMe() {
        /**
         * 以AtomicInteger为例，其它七种类型类似
         * ***/
        //以下操作为原子操作
        AtomicInteger atomicInteger = new AtomicInteger(1);
        int i = atomicInteger.incrementAndGet();//自增1且返回之前的值
        int i1 = atomicInteger.get();//获得当前值
        atomicInteger.compareAndSet(0, 1);//符合期望值就设置新值
        atomicInteger.lazySet(1);//总会设置新值

        /**
         * 数组类型
         * */
        int[] arr = new int[5];
        AtomicIntegerArray array = new AtomicIntegerArray(arr);
        array.compareAndSet(0, 0, 1);

        /**
         * 引用类型
         * */
        Object object = new Object();
        AtomicReference<Object> reference = new AtomicReference<>(object);
        reference.compareAndSet(object, new Object());

        /**
         * 字段类型
         * */
        AtomicIntegerFieldUpdater<User> age = AtomicIntegerFieldUpdater.newUpdater(User.class, "age");
        age.compareAndSet(new User(), 0, 2);

    }

    static class User {
        volatile int age;
    }
}
