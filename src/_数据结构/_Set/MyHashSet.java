package _数据结构._Set;

import _数据结构._Map.MyHashMap;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * @program: suanfa_by_java
 * @author: 一树
 * @data: 2020/11/16 19:50
 */
public class MyHashSet<T> implements Iterable<T> {
    private final Object P = new Object();
    private int size;
    private MyHashMap<T, Object> map;

    public MyHashSet() {
        map = new MyHashMap<>();
        size = 0;
    }

    public MyHashSet(Collection<? extends T> c) {
        map = new MyHashMap<>();
        size = 0;
        for (T t : c) {
            add(t);
        }
    }

    public void add(T t) {
        if (!map.containKey(t)) {
            map.put(t, P);
            size++;
        }
    }

    public void remove(T t) {
        if (map.containKey(t)) {
            map.remove(t);
        }
    }

    public int size() {
        return size;
    }

    public boolean contain(T t) {
        return map.containKey(t);
    }


    @Override
    public Iterator<T> iterator() {
        return map.enterKey().iterator();
    }

}
