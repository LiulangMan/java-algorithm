package _设计模式._行为型._观察者模式;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: Algorithm_by_java
 * @author: 一树
 * @data: 2020/11/1 18:35
 */

@SuppressWarnings("all")
public class _readMe {
    /*
     * 观察者模式也叫做发布订阅模式，定义了对象之间一对多的关系，
     * 当主体对象发生改变时，他的所有观察者都会收到对应消息并且更新，属于行为模式。
     *
     * 比如MQ,朋友圈等都属于此模式
     */

    public static void main(String[] args) {
        //被观察者
        ISubject<String> subject = new ConcreteSubject<>();

        //观察者
        IObserver<String> observer = new ConcreteObserver<>();
        IObserver<String> observer2 = new ConcreteObserver<>();

        //注册
        subject.attach(observer);
        subject.attach(observer2);

        //通知
        subject.notify("hello");
    }

    //抽象观察者
    interface IObserver<E> {
        void update(E event);
    }

    //抽象主题者
    public interface ISubject<E> {
        boolean attach(IObserver<E> observer);

        boolean detach(IObserver<E> observer);

        void notify(E event);
    }

    //具体观察者
    static class ConcreteObserver<E> implements IObserver<E> {

        @Override
        public void update(E event) {
            System.out.println("receive event：" + event);
        }
    }

    //具体主题者
    static class ConcreteSubject<E> implements ISubject<E> {

        private List<IObserver<E>> observers = new ArrayList<>();

        @Override
        public boolean attach(IObserver<E> observer) {
            return !this.observers.contains(observer) && this.observers.add(observer);
        }

        @Override
        public boolean detach(IObserver<E> observer) {
            return this.observers.remove(observer);
        }

        @Override
        public void notify(E event) {
            for (IObserver<E> observer : observers) {
                observer.update(event);
            }
        }
    }


}
