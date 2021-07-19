package _设计模式._结构型._代理模式._JDK动态代理;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/***
 * JDK动态代理的方式，利用接口实现代理
 *
 * ***/
public class ProxyFactory implements InvocationHandler {
    private Object target;

    private Object getInstance(Object target) {
        this.target = target;
        Object instance = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
        return instance;
    }

    public static <T> T getInstance(Class<T> clazz) {
        try {
            T t = clazz.newInstance();
            ProxyFactory factory = new ProxyFactory();
            Object instance = factory.getInstance(t);
            return (T) instance;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object object = method.invoke(this.target, args);
        after();
        return object;
    }

    private void before() {
        System.out.println("before");
    }

    private void after() {
        System.out.println("after");
    }

    //test
    public static void main(String[] args) {
        User instance = ProxyFactory.getInstance(UserVo.class);
        instance.walk();
    }
}
