package Spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @program: suanfa_by_java
 * @author: 一树
 * @data: 2020/11/18 9:07
 */
public class Resource {
    public static void main(String[] args) {
        //注解入口
        AnnotationConfigApplicationContext context1 = new AnnotationConfigApplicationContext("Spring.Server");
        Object bean1 = context1.getBean("server");
        System.out.println(bean1.getClass().getName());
    }
}
