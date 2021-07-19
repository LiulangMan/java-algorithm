package _设计模式._结构型._桥接模式;

/**
 * @program: algorithm_by_java
 * @author: 一树
 * @data: 2020/12/1 15:11
 */
public class Client {
    public static void main(String[] args) {
        //创建一个具体角色
        ConcreteImplementorA implementorA = new ConcreteImplementorA();
        //创建一个抽象角色，聚合实现
        RefineAbstraction abs = new RefineAbstraction(implementorA);
        //执行操作
        abs.operation();
    }

    //抽象
    static abstract class Abstraction{
        protected Implementor implementor;

        public Abstraction(Implementor implementor) {
            this.implementor = implementor;
        }

        public void operation(){
            this.implementor.operationImpl();
        }
    }

    //修正抽象
    static class RefineAbstraction extends Abstraction{

        public RefineAbstraction(Implementor implementor) {
            super(implementor);
        }

        @Override
        public void operation() {
            super.operation();
            System.out.println("refined operation");
        }
    }


    //抽象实现
    interface Implementor{
        void operationImpl();
    }

    //具体实现
    static class ConcreteImplementorA implements Implementor{

        @Override
        public void operationImpl() {
            System.out.println("I am ConcreteImplementorA");
        }
    }

    static class ConcreteImplementorB implements Implementor{

        @Override
        public void operationImpl() {
            System.out.println("I am ConcreteImplementorB");
        }
    }
}
