package _设计模式._结构型._装饰者模式;

/**
 * @program: suanfa_by_java
 * @author: 一树
 * @data: 2020/11/1 16:48
 */


/*抽象煎饼类*/
public abstract class BatterCake {
    protected abstract String getMsg();

    protected abstract int getPrice();
}

/*基类*/
class BaseBatterCake extends BatterCake {

    @Override
    protected String getMsg() {
        return "煎饼";
    }

    @Override
    protected int getPrice() {
        return 5;
    }
}


/*扩展套餐的抽象装饰类BatterCakeDecorator*/

abstract class BatterCakeDecorator extends BatterCake {
    //静态代理，委派
    private BatterCake batterCake;

    public BatterCakeDecorator(BatterCake batterCake) {
        this.batterCake = batterCake;
    }

    protected abstract void doSomething();

    @Override
    protected int getPrice() {
        return this.batterCake.getPrice();
    }

    @Override
    protected String getMsg() {
        return this.batterCake.getMsg();
    }
}

/*创建鸡蛋装饰者*/

class EggDecorator extends BatterCakeDecorator {


    public EggDecorator(BatterCake batterCake) {
        super(batterCake);
    }

    @Override
    protected void doSomething() {

    }


    @Override
    protected String getMsg() {
        return super.getMsg() + " +1鸡蛋";
    }

    @Override
    protected int getPrice() {
        return super.getPrice() + 1;
    }
}

/*创建香肠装饰者*/

class SausageDecorator extends BatterCakeDecorator {


    public SausageDecorator(BatterCake batterCake) {
        super(batterCake);
    }

    @Override
    protected void doSomething() {

    }


    @Override
    protected String getMsg() {
        return super.getMsg() + " +1香肠";
    }

    @Override
    protected int getPrice() {
        return super.getPrice() + 2;
    }
}

class Test{

    public static void main(String[] args) {
        //路边买了一个煎饼
        BatterCake batterCake = new BaseBatterCake();
        //加一个鸡蛋
        batterCake = new EggDecorator(batterCake);
        //再加一个鸡蛋
        batterCake = new EggDecorator(batterCake);
        //再加一个香肠
        batterCake = new SausageDecorator(batterCake);

        System.out.println("batterCake.getMsg() = " + batterCake.getMsg());
        System.out.println("batterCake.getPrice() = " + batterCake.getPrice());
    }
}