package _数据结构._Map;

import java.util.*;

/**
 * @program: suanfa_by_java
 * @author: 一树
 * @data: 2020/11/16 9:49
 */
public class MyHashMap<K, V> {

    private static final int DEFAULT_CAPACITY = 17;
    private static final float DEFAULT_LOAD = 0.75f;
    private static final int MAX_CAPACITY = (1 << 30) + 1;
    private int size;
    private int resizeCount;
    private Node<K, V>[] table;
    private int tableLength;
    private float load;


    @SuppressWarnings("unchecked")
    public MyHashMap(int capacity, float load) {
        this.tableLength = capacity;
        this.load = load;
        this.table = (Node<K, V>[]) new Node[capacity];
    }

    @SuppressWarnings("unchecked")
    public MyHashMap(int capacity) {
        this.tableLength = capacity;
        this.load = DEFAULT_LOAD;
        this.table = (Node<K, V>[]) new Node[capacity];
    }

    @SuppressWarnings("unchecked")
    public MyHashMap() {
        this.load = DEFAULT_LOAD;
        this.tableLength = DEFAULT_CAPACITY;
        this.table = (Node<K, V>[]) new Node[tableLength];
    }

    @SuppressWarnings("unchecked")
    public MyHashMap(Map<? extends K, ? extends V> map) {
        this.load = DEFAULT_LOAD;
        this.tableLength = DEFAULT_CAPACITY;
        this.table = (Node<K, V>[]) new Node[tableLength];
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            K key = entry.getKey();
            V value = entry.getValue();
            put(key, value);
        }
    }

    /*扩容*/
    @SuppressWarnings("unchecked")
    private void resize() {
        int newCapacity = Math.min(MAX_CAPACITY, (tableLength << 1) + 1);
        Node<K, V>[] oldTable = this.table;
        this.table = (Node<K, V>[]) new Node[newCapacity];
        this.tableLength = newCapacity;
        //将oldItem中的数重新散列
        for (Node<K, V> p : oldTable) {
            while (p != null) {
                K key = p.getKey();
                V value = p.getValue();
                put(key, value);
                p = p.next;
            }
        }
    }


    public void put(K k, V v) {
        this.putVal(k, v, hash(k, tableLength), false);
    }

    public void putIfAbsent(K k, V v) {
        this.putVal(k, v, hash(k, tableLength), true);
    }

    private void putVal(K k, V v, int hash, boolean abSent) {
        Node<K, V> p = table[hash];
        Node<K, V> pre = null;
        while (p != null) {
            if (p.key.equals(k)) {
                if (!abSent) p.value = v;
                return;
            }
            pre = p;
            p = p.next;
        }
        p = new Node<>(hash, k, v, null);
        if (pre == null) {
            table[hash] = p;
            resizeCount++;//记录表被占用的数
        } else {
            pre.next = p;
        }
        size++;
        if (resizeCount >= load * tableLength) {
            resize();
        }
    }
    
    public int size() {
        return size;
    }

    public V get(K k) {
        Node<K, V> p = table[hash(k, tableLength)];

        while (p != null) {
            if (k.equals(p.key)) return p.value;
            p = p.next;
        }

        return null;
    }

    public void remove(K k) {
        int index = hash(k, tableLength);
        Node<K, V> p = table[index];
        Node<K, V> pre = null;
        while (p != null) {
            if (k.equals(p.key)) {
                Node<K, V> next = p.next;
                if (next != null && pre != null) {
                    pre.next = next;
                    p.next = null;
                    size--;
                    return;
                }

                if (next == null && pre != null) {
                    pre.next = null;
                    size--;
                    return;
                }

                if (pre == null) {
                    table[index] = null;
                    size--;
                    return;
                }
            }
            pre = p;
            p = p.next;
        }
    }


    public V set(K k, V v) {
        V old = get(k);
        put(k, v);
        return old;
    }

    public boolean containKey(K k) {
        return get(k) != null;
    }

    //散列函数
    private static int hash(Object key, int tableSize) {
        String h = key.toString();
        int hash = 0;
        for (int i = 0; i < h.length(); i++) {
            hash = 37 * hash + h.charAt(i);
        }
        hash %= tableSize;
        if (hash < 0)
            hash += tableSize;
        return hash;
    }


    /*>>>>>>>>>>>>>>>>>>>>>>>>>>class Node>>>>>>>>>>>>>>>>>>>>>>*/
    public static class Node<K, V> {
        int hash;
        K key;
        V value;
        Node<K, V> next;

        Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }


    /*>>>>>>>>>>>>>>>>>>>Iterator>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/

    public Set<Node<K, V>> enterSet() {

        Set<Node<K, V>> entrySet = new HashSet<>();
        //将数据装入set中
        for (int i = 1; i < tableLength; i++) {
            if (table[i] != null) {
                Node<K, V> p = table[i];
                while (p != null) {
                    entrySet.add(p);
                    p = p.next;
                }
            }
        }
        return entrySet;
    }

    public Set<K> enterKey() {
        Set<K> entryKey = new HashSet<>();
        //将数据装入set中
        for (int i = 1; i < tableLength; i++) {
            if (table[i] != null) {
                Node<K, V> p = table[i];
                while (p != null) {
                    entryKey.add(p.key);
                    p = p.next;
                }
            }
        }
        return entryKey;
    }

    public Set<V> enterValue() {
        Set<V> entryValue = new HashSet<>();
        //将数据装入set中
        for (int i = 1; i < tableLength; i++) {
            if (table[i] != null) {
                Node<K, V> p = table[i];
                while (p != null) {
                    entryValue.add(p.value);
                    p = p.next;
                }
            }
        }
        return entryValue;
    }

    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/

}
