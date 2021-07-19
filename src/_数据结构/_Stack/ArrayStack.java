package _数据结构._Stack;

import java.util.Collection;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @program: suanfa_by_java
 * @author: 一树
 * @data: 2020/11/15 13:47
 */
public class ArrayStack<T> implements Iterable<T> {

    private static final int DEFAULT_CAPACITY = 10;

    private int size;

    private T[] Items;


    public ArrayStack() {
        doInit();
        ensureCapacity(DEFAULT_CAPACITY);
    }

    public ArrayStack(int initCapacity) {
        doInit();
        ensureCapacity(initCapacity);
    }

    public ArrayStack(Collection<? extends T> c) {
        doInit();
        ensureCapacity(DEFAULT_CAPACITY);
        for (T t : c) {
            this.push(t);
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

    public boolean isEmpty() {
        return size == 0;
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

    public T peek() {
        return Items[size - 1];
    }


    public void push(T value) {

        if (size() == Items.length) {
            //扩容
            ensureCapacity(2 * size() + 1);
        }

        Items[size()] = value;
        size++;
    }


    public T pop() {
        if (size == 0)
            throw new EmptyStackException();
        return Items[--size];
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

    @Override
    public Iterator<T> iterator() {
        return new ArrayStackIterator();
    }

    private class ArrayStackIterator implements java.util.Iterator<T> {

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
            ArrayStack.this.pop();
        }
    }
}


