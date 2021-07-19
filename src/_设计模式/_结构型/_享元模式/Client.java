package _设计模式._结构型._享元模式;

/**
 * @program: algorithm_by_java
 * @author: 一树
 * @data: 2020/12/1 12:30
 */


public class Client {
    public static void main(String[] args) {
        MyInteger myInteger = MyInteger.valueOf(11);
        MyInteger myInteger2 = MyInteger.valueOf(11);
        MyInteger myInteger3 = MyInteger.valueOf(1111);
        MyInteger myInteger4 = MyInteger.valueOf(1111);
        System.out.println(myInteger==myInteger2);
        System.out.println(myInteger4==myInteger3);
    }
}

@SuppressWarnings("all")
class MyInteger {
    private int value;
    private static MyInteger[] cache = new MyInteger[256];

    static {
        for (int i = 0; i < 256; i++) {
            cache[i] = new MyInteger(i - 127);
        }
    }

    public MyInteger(int i) {
        this.value = i;
    }

    //享元模式+构造者模式
    public static MyInteger valueOf(int i) {
        if (i >= -127 && i <= 128) {
            return cache[i + 127];
        }
        return new MyInteger(i);
    }

    @Override
    public String toString() {
        return "MyInteger{" +
                "value=" + value +
                '}';
    }
}

