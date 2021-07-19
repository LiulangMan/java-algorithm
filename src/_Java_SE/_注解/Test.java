package _Java_SE._注解;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Point("class")
public class Test implements InvocationHandler {
    @Point("f1")
    public void f1() {
    }

    @Point("f2")
    public void f2() {
    }

    @Point("f3")
    public void f3() {
    }

    public static void main(String[] args) {
        print_annotation(Test.class);

    }

    public static void print_annotation(Class<?> clazz) {
        if (clazz.isAnnotationPresent(Point.class)) {
            Point point = clazz.getAnnotation(Point.class);
            System.out.println(clazz.getName()+" @" +point.value());
        }

        Method[] methods = clazz.getMethods();
        for (Method e : methods) {
            print_annotation(e);
        }

    }

    public static void print_annotation(Method method) {
        Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
        for (Annotation a : declaredAnnotations) {
            if (a instanceof Point) {
                Point p = (Point) a;
                System.out.println(method.getName()+" was annotation @" +p.getClass().getName()+":"+p.value());
            }
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //before();
        Object invoke = method.invoke(proxy, args);
        return invoke;

    }

    public <T> T getProxy(Class<?> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }
}
