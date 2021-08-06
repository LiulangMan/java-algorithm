package _Java_SE._泛型._T;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @program: suanfa_by_java
 * @author: 一树
 * @data: 2020/11/8 22:13
 */
public class MyMap {

    private Map<Class<?>, Object> map = new HashMap<>();

    public <T> void putValue(Class<T> type, T instance) {
        map.put(Objects.requireNonNull(type), instance);
    }

    public <T> T getValue(Class<T> type) {
        return type.cast(map.get(type));
    }

    public static void main(String[] args) {
        MyMap myMap = new MyMap();
        myMap.putValue(Integer.class,11);
        myMap.putValue(String.class,"");
        Integer value = myMap.getValue(Integer.class);
        String value1 = myMap.getValue(String.class);
        System.out.println(value);
        System.out.println(value1);
    }
}
