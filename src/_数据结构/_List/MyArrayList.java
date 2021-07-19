package _数据结构._List;


import java.util.*;

/**
 * @program: suanfa_by_java
 * @author: 一树
 * @data: 2020/11/14 19:21
 */

//注意，实现的接口是java.lang.Iterable<T>
public class MyArrayList<T> implements Iterable<T> {

    private static final int DEFAULT_CAPACITY = 10;

    private int size;

    private T[] Items;

    public MyArrayList() {
        doInit();
        ensureCapacity(DEFAULT_CAPACITY);
    }

    public MyArrayList(int initCapacity) {
        doInit();
        ensureCapacity(initCapacity);
    }

    public MyArrayList(Collection<? extends T> c) {
        doInit();
        ensureCapacity(DEFAULT_CAPACITY);
        for (T t : c) {
            this.add(t);
        }
    }

    @SuppressWarnings("unchecked")
    public void clear() {
        doInit();
        Items = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public int size() {
        return size;
    }

    private void doInit() {
        this.size = 0;
    }

    @SuppressWarnings("unchecked")
    private void ensureCapacity(int newCapacity) {
        if (newCapacity < 0) {
            System.out.println("数组大小不能为负");
            System.exit(0);
        }
        if (newCapacity < size) return;
        T[] old = Items;
        Items = (T[]) new Object[newCapacity];
        for (int i = 0; i < size(); i++) {
            Items[i] = old[i];
        }

    }

    public T set(int index, T value) {
        checkIndex(index);
        T old = Items[index];
        Items[index] = value;
        return old;
    }

    public T get(int index) {
        checkIndex(index);
        return Items[index];
    }


    public void add(T value) {

        if (size() == Items.length) {
            //扩容
            ensureCapacity(2 * size() + 1);
        }

        Items[size()] = value;
        size++;
    }

    public void addAll(Collection<? extends T> c) {
        for (T t : c) {
            this.add(t);
        }
    }


    public void remove(int index) {
        checkIndex(index);
        for (int i = index; i < size() - 1; i++) {
            Items[i] = Items[i + 1];
        }
        size--;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(Items[i]);
            sb.append(',');
        }

        sb.delete(sb.length() - 1, sb.length());
        sb.append(']');
        return sb.toString();
    }

    @SuppressWarnings("unchecked")
    public void sort(Comparator<? super T> c) {
        T[] a = (T[]) new Object[size];
        for (int i = 0; i < size; i++) {
            a[i] = Items[i];
        }
        Arrays.sort(a, c);
        for (int i = 0; i < size; i++) {
            Items[i] = a[i];
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements java.util.Iterator<T> {

        private int current = 0;

        @Override
        public boolean hasNext() {
            return current < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (T) Items[current++];
        }

        @Override
        public void remove() {
            MyArrayList.this.remove(--current);
        }

    }
}
