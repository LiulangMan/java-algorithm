package _设计模式._行为型._中介者模式;

/**
 * @program: algorithm_by_java
 * @author: 一树
 * @data: 2020/12/16 14:56
 * @Text: 类似于路由器，如果没有路由器，PC间访问就需要各个连接，不利于拓展。因此，路由器相当于中介者。
 */

@SuppressWarnings("all")
public class Client {

    public static void main(String[] args) {
        Mediator mediator = new ConcreteMediator();
        ConcreteColleagueA colleagueA = new ConcreteColleagueA(mediator);
        ConcreteColleagueB colleagueB = new ConcreteColleagueB(mediator);

        colleagueA.depMethod();
        System.out.println("---------------");
        colleagueB.depMethod();
    }


    //抽象同事类
    static abstract class Colleague {
        protected Mediator mediator;

        public Colleague(Mediator mediator) {
            this.mediator = mediator;
        }
    }

    //具体同事类A
    static class ConcreteColleagueA extends Colleague {

        public ConcreteColleagueA(Mediator mediator) {
            super(mediator);
            this.mediator.setCollageA(this);
        }

        //自发行为：Self-Method
        public void selfMetodA() {
            //处理自己的逻辑
            System.out.println(this.getClass().getSimpleName() + ",self-Method");
        }

        //依赖方法:Dep-Method
        public void depMethod() {
            //处理自己的逻辑
            System.out.println(this.getClass().getSimpleName() + ",depMethod:delegate to Mediator");
            //无法处理的业务委托给中介者处理
            this.mediator.transferA();
        }
    }

    //具体同事类B
    static class ConcreteColleagueB extends Colleague {

        public ConcreteColleagueB(Mediator mediator) {
            super(mediator);
            this.mediator.setCollageB(this);
        }

        //自发行为：Self-Method
        public void selfMetodA() {
            //处理自己的逻辑
            System.out.println(this.getClass().getSimpleName() + ",self-Method");
        }

        //依赖方法:Dep-Method
        public void depMethod() {
            //处理自己的逻辑
            System.out.println(this.getClass().getSimpleName() + ",depMethod:delegate to Mediator");
            //无法处理的业务委托给中介者处理
            this.mediator.transferB();
        }
    }

    //抽象中介者
    static abstract class Mediator{

        protected ConcreteColleagueA colleagueA;
        protected ConcreteColleagueB colleagueB;

        public void setCollageB(ConcreteColleagueB concreteColleagueB){
            this.colleagueB = concreteColleagueB;
        }

        public void setCollageA(ConcreteColleagueA concreteColleagueA){
            this.colleagueA = concreteColleagueA;
        }

        //中介者业务处理
        public abstract void transferB();

        public abstract void transferA();
    }

    //具体中介者
    static class ConcreteMediator extends Mediator{

        @Override
        public void transferB() {
            //协助行为：B转发到A
            this.colleagueA.selfMetodA();
        }

        @Override
        public void transferA() {
            this.colleagueB.selfMetodA();
        }
    }

}
