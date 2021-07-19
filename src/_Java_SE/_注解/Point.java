package _Java_SE._注解;


import java.lang.annotation.*;

@Target(value = {ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
//@Inherited
public @interface Point {
    String value() default "";

    String[] value2() default {};

    int[] value3() default {};

    Class<?>[] value4() default {};
}
