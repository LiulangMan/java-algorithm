package _设计模式._创建型._建造者;

/**
 * @program: algorithm_by_java
 * @author: 一树
 * @data: 2020/11/30 16:06
 */

public class BuildTest {
    //客户端
    public static void main(String[] args) {
        A.Builder builder = new A.Builder();
        A instance = builder.setA(1).setB(2).setC(3).build();
        System.out.println(instance);
    }
}

class A{
    private int a;
    private int b;
    private int c;
    A(){}

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public static class Builder{
        A instance;

        public Builder() {
            instance = new A();
        }

        public A build(){
            return instance;
        }

        public Builder setA(int a){
            this.instance.a = a;
            return this;
        }

        public Builder setB(int b){
            this.instance.b = b;
            return this;
        }

        public Builder setC(int c){
            this.instance.c = c;
            return this;
        }
    }

    @Override
    public String toString() {
        return "A{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                '}';
    }
}
