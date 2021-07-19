package _tmp;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: algorithm_by_java
 * @author: 一树
 * @data: 2020/12/10 17:36
 */
public class _泛型擦除实例 {
    public static void main(String[] args) {
        List<Integer> list  = new ArrayList<>();
        List<String> list1  = new ArrayList<>();
        list.add(1);
        Integer integer = list.get(0);
        list1.add("1");
        System.out.println(list1.get(0));

    }
}
