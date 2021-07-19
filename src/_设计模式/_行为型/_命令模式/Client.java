package _设计模式._行为型._命令模式;

/**
 * @program: algorithm_by_java
 * @author: 一树
 * @data: 2020/12/16 13:52
 */

@SuppressWarnings("all")
public class Client {

    public static void main(String[] args) {
        ConcreteCommand concreteCommand = new ConcreteCommand();
        Invoker invoker = new Invoker(concreteCommand);
        invoker.action();
    }

    //接收者
    static class Receiver {
        public void action() {
            System.out.println("执行具体操作");
        }
    }

    //抽象命令
    interface ICommand {
        void execute();
    }

    //具体命令
    static class ConcreteCommand implements ICommand {
        private Receiver receiver = new Receiver();

        @Override
        public void execute() {
            receiver.action();
        }
    }


    //请求者
    static class Invoker {
        private ICommand command;

        public Invoker(ICommand command) {
            this.command = command;
        }

        public void action() {
            this.command.execute();
        }
    }

}
