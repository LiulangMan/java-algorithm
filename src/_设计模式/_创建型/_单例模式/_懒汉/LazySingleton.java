package _设计模式._创建型._单例模式._懒汉;

import java.io.Serializable;
import java.lang.reflect.Constructor;

public class LazySingleton implements Serializable {
    private volatile static LazySingleton instance = null;

    private LazySingleton() {
        if (Lazy_load.LAZY_SINGLETON != null) {
            throw new RuntimeException("破坏模式");//防止反射破坏单例模式
        }
    }

    //synchronized
    public static LazySingleton getInstance() {

        if (instance == null) {
            synchronized (LazySingleton.class) {
                if (instance == null) {
                    instance = new LazySingleton();//volatile防止new 对象重排序
                }
            }
        }

        return instance;
    }

    //采用静态内部类线程安全
    public static final LazySingleton getInstance2() {
        return Lazy_load.LAZY_SINGLETON;
    }

    private static class Lazy_load {
        private static final LazySingleton LAZY_SINGLETON = new LazySingleton();
    }

    //防止反序列化破坏单例
    private Object readResolve() {
        return instance;
    }

    //test
    public static void main(String[] args) {

        try {
            Class<?> clazz = LazySingleton.class;
            Constructor constructor = clazz.getDeclaredConstructor((Class<?>) null);
            constructor.setAccessible(true);
            //初始化两次，犯原则性错误
            Object o1 = constructor.newInstance();
            Object o2 = constructor.newInstance();
            System.out.println(o1 == o2);
        } catch (Exception e) {
            e.printStackTrace();
            ;
        }
    }
}

//更加优雅的写法
enum Singleton {
    INSTANCE;

    private Object data;

    public void setData(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public static Singleton getInstance() {
        return INSTANCE;
    }
}
