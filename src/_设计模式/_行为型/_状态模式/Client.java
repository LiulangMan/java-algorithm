package _设计模式._行为型._状态模式;

/**
 * @program: algorithm_by_java
 * @author: 一树
 * @data: 2020/12/16 14:00
 */

@SuppressWarnings("all")
public class Client {
    public static void main(String[] args) {
        Context context = new Context();
        context.setState(new ConcreteStateA());
        context.handler();
    }

    //抽象状态类
    static abstract class State{
        protected Context context;

        public void setContext(Context context) {
            this.context = context;
        }

        public abstract void handler();
    }

    //具体状态类A
    static class ConcreteStateA extends State{

        @Override
        public void handler() {
            System.out.println("StateA do action");
            //A状态完成后自动切换为B状态
            this.context.setState(Context.STATE_B);
            this.context.getState().handler();
        }
    }

    //具体状态类A
    static class ConcreteStateB extends State{

        @Override
        public void handler() {
            System.out.println("StateB do action");
        }
    }

    //环境类
    static class Context{
        public static final State STATE_A = new ConcreteStateA();
        public static final State STATE_B = new ConcreteStateB();

        //默认状态A
        private State currentState = STATE_A;
        {
            STATE_A.setContext(this);
            STATE_B.setContext(this);
        }

        public void setState(State state){
            this.currentState = state;
            this.currentState.setContext(this);
        }

        public State getState() {
            return currentState;
        }

        public void handler(){
            this.currentState.handler();
        }

    }
}
