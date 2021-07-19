package _设计模式._创建型._单例模式._饿汉;

public class HungrySingleton {
    private final static HungrySingleton LAZY_SINGLETON;
    static {
        LAZY_SINGLETON = new HungrySingleton();
    }

    private HungrySingleton(){}

    public static HungrySingleton getInstance(){
        return LAZY_SINGLETON;
    }


}
