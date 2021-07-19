package _设计模式._创建型._方法工厂;

/**
 * @program: algorithm_by_java
 * @author: 一树
 * @data: 2020/11/30 17:07
 */
public class Client {
    public static void main(String[] args) {
        AFactory aFactory = new AFactory();
        BFactory bFactory = new BFactory();
        Product product = aFactory.createProduct();
        Product product1 = bFactory.createProduct();
        product.doSomeThing();
        product1.doSomeThing();
    }
}

//工厂规范
interface Factory {
    Product createProduct();
}

//产品规范
interface Product {
    void doSomeThing();
}

//A产品
class AProduct implements Product {

    @Override
    public void doSomeThing() {
        System.out.println("I am A");
    }
}

//A产品
class BProduct implements Product {

    @Override
    public void doSomeThing() {
        System.out.println("I am B");
    }
}

//A工厂
class AFactory implements Factory {

    @Override
    public Product createProduct() {
        return new AProduct();
    }
}

//B工厂
class BFactory implements Factory {

    @Override
    public Product createProduct() {
        return new BProduct();
    }
}