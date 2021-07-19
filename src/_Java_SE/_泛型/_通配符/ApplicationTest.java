package _Java_SE._泛型._通配符;

import java.util.*;

/**
 * @program: suanfa_by_java
 * @author: 一树
 * @data: 2020/11/6 11:02
 * <p>
 * PECS法则：producer <? extends E>
 * consumer <? supers E>
 */
public class ApplicationTest {

    public static <E> Set<E> union(Set<? extends E> set1, Set<? extends E> set2) {
        Set<E> result = new HashSet<>(set1);
        result.addAll(set2);
        return result;
    }


    //E需要实现Comparable接口,才可以调用compareTo()
    public static <E extends Comparable<? super E>> E max(Set<? extends E> set) {
        if (set.isEmpty()) {
            throw new IllegalArgumentException("Empty Collection!");
        }

        E result = null;
        for (E e : set) {
            if (result == null || e.compareTo(result) > 0) {
                result = Objects.requireNonNull(e);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Set<Integer> set1 = new HashSet<>(Arrays.asList(1, 3, 5));
        Set<Double> set2 = new HashSet<>(Arrays.asList(1.1, 1.3, 1.5));

        Set<Number> numbers = union(set1, set2);  //取元素时只能是Number，而不能添加元素
        for (Number number : numbers) {
            System.out.println(number);
        }

        System.out.println(max(set1));
    }
}
