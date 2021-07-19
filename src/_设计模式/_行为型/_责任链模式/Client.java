package _设计模式._行为型._责任链模式;

/**
 * @program: algorithm_by_java
 * @author: 一树
 * @data: 2020/12/16 13:11
 * @Text: 将链中每个节点看作一个对象，每个节点处理的请求均不同，且内部自动维护下一个节点对象。
 * 当一个请求从链式的首端发出时，会沿着责任链预设的道路依次传递到每一个节点对象，直至被链中某
 * 个对象处理为止。
 */

@SuppressWarnings("all")
public class Client {

    public static void main(String[] args) {
        Handler handlerA = new ConcreteHandlerA();
        Handler handlerB = new ConcreteHandlerB();
        handlerA.setNextHandler(handlerB);
        handlerA.handlerRequest("requestB");

    }


    //抽象Handler
    static abstract class Handler {
        protected Handler nextHandler;

        public void setNextHandler(Handler nextHandler) {
            this.nextHandler = nextHandler;
        }

        public abstract void handlerRequest(String request);
    }


    //具体处理者A
    static class ConcreteHandlerA extends Handler {

        @Override
        public void handlerRequest(String request) {
            if ("requestA".equals(request)) {
                System.out.println(this.getClass().getName() + " deal with request:" + request);
                return;
            }
            if (nextHandler != null) {
                this.nextHandler.handlerRequest(request);//传递下去
            }
        }
    }


    //具体处理者B
    static class ConcreteHandlerB extends Handler {

        @Override
        public void handlerRequest(String request) {
            if ("requestB".equals(request)) {
                System.out.println(this.getClass().getName() + " deal with request:" + request);
                return;
            }
            if (nextHandler != null) {
                this.nextHandler.handlerRequest(request);//传递下去
            }
        }
    }
}
