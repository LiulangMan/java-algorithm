package _算法._缓存算法;

import java.util.HashMap;
import java.util.Scanner;

//hashMap + LinkList
public class LRU<K, V> {

    private int DEFAULT_CACHE_SIZE = 16;
    private HashMap<K, Enter> map;
    private Enter first;
    private Enter last;

    public LRU(int cacheSize) {
        if (cacheSize <= 0) {
            throw new IllegalArgumentException("size <= 0!");
        }
        this.DEFAULT_CACHE_SIZE = cacheSize;
        map = new HashMap<>();
    }

    private class Enter {
        private Enter next;
        private Enter pre;
        private K key;
        private V value;
    }

    public V get(K key) {
        if (map.get(key) != null) {
            Enter enter = map.get(key);

            //初始化
            if (first == null || last == null) {
                first = last = enter;
                return enter.value;
            }
            //首部
            if (enter == first) return enter.value;
            //尾部
            if (enter == last) {
                if (enter.pre != null) last = enter.pre;
                last.next = null;
                enter.next = first;
                enter.pre = null;
                first.pre = enter;
                first = enter;
                return enter.value;
            }

            //usually
            if (enter.pre != null) enter.pre.next = enter.next;
            if (enter.next != null) enter.next.pre = enter.pre;
            enter.next = first;
            enter.pre = null;
            first.pre = enter;
            first = enter;
            return enter.value;
        }

        return null;
    }

    public V set(K key, V value) {

        if (map.get(key) != null) {
            map.get(key).value = value;//update value
            return get(key);
        }
        //空
        Enter enter = new Enter();
        enter.key = key;
        enter.value = value;

        if (map.size() < DEFAULT_CACHE_SIZE) {
            map.put(key, enter);
            return get(key);

        } else {

            //移除最后一个
            map.remove(last.key);
            Enter new_last = last.pre;
            new_last.next = null;
            last.pre = null;
            last = new_last;
            map.put(key, enter);
            return get(key);

        }
    }

    private void printHash() {
        Enter p = first;

        while (p != null) {
            System.out.print(p.value + " ");
            p = p.next;
        }
        System.out.println();
    }

    public static void main(String[] args){
        LRU<Integer, String> myLru = new LRU<>(5);
        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt();
        for (int i = 0; i < m; i++) {
            myLru.set(sc.nextInt(), sc.next());
            myLru.printHash();
        }
    }
}