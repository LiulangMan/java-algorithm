import _多线程编程._ThreadLocal.MyThreadLocal;

import java.lang.annotation.*;
import java.lang.reflect.Method;

/**
 * @program: algorithm_by_java
 * @author: 一树
 * @time: 2021/5/6 22:59
 */

@Documented
@Target(value = {ElementType.FIELD, ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface Tag {

    String value() default "default";

}

@Tag("this is temp")
public class Temp {

    private int tag = 0;

    public Temp(int tag) {
        this.tag = tag;
    }

    @Tag("this is sun()")
    public int sum(int[] arr){
        return 0;
    };

    private static MyThreadLocal<Temp> tempThreadLocal = new MyThreadLocal<>();

    public static void testThreadLocal(){


        for (int i = 0; i < 100; i++) {
            int finalI = i;
            new Thread(()->{
                tempThreadLocal.set(new Temp(finalI));
                System.out.println(Thread.currentThread().getName());
                System.out.println(tempThreadLocal.get());
            }).start();
        }
    }



    public static void main(String[] args) {
        try {
//            Class<?> clazz = Class.forName("Temp.java");
//
//            Temp obj = (Temp)clazz.newInstance();

            Temp obj = new Temp(0);

            for (Annotation annotation : obj.getClass().getAnnotations()) {
                if (annotation instanceof Tag){
                    System.out.println(((Tag) annotation).value());
                }
            }


            for (Method method : obj.getClass().getMethods()) {

                if (method.isAnnotationPresent(Tag.class)){
                    Tag tag = method.getAnnotation(Tag.class);
                    System.out.println(tag.value());
                }
            }


            System.out.println("--------------------------------------");
            testThreadLocal();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}