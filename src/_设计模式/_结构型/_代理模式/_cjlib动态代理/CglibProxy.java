package _设计模式._结构型._代理模式._cjlib动态代理;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {

    public Object getInstance(Class<?> clazz) throws Exception {
        Enhancer enhancer = new Enhancer();
        //设置成父类
        enhancer.setSuperclass(clazz);
        //设置成子类
        enhancer.setCallback(this);

        return enhancer.create();

    }

    public <T> T getInstance(T t) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(t.getClass());
        enhancer.setCallback(this);
        return (T) enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        before();
        Object obj = methodProxy.invokeSuper(o, objects);
        after();
        return obj;
    }


    private void before() {
        System.out.println("before");
    }

    private void after() {
        System.out.println("after");
    }


    //test
    public static void main(String[] args) throws Exception {
        CglibProxy cglibProxy = new CglibProxy();
        User user = new User();
        User proxyInstance = cglibProxy.getInstance(user);
        proxyInstance.walk();
    }
}
