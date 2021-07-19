package _数据结构._Stack;

import java.util.Collection;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @program: suanfa_by_java
 * @author: 一树
 * @data: 2020/11/15 14:18
 */
public class LinkedStack<T> implements Iterable<T> {

    private int size;
    private Node<T> beginMarker;
    private Node<T> endMarker;

    public LinkedStack() {
        doInit();
    }

    public LinkedStack(Collection<? extends T> c) {
        doInit();
        for (T t : c) {
            push(t);
        }
    }

    private void doInit() {
        size = 0;
    }

    public void pushAll(Collection<? extends T> others) {
        for (T other : others) {
            push(other);
        }
    }

    //内部类
    private class Node<T> {
        T value;
        Node<T> next;
        Node<T> prefix;

        public Node(T value) {
            this.value = value;
        }
    }

    private Node<T> getNode(int index) {
        checkIndex(index);
        Node<T> p;
        int cnt = 0;
        int limit = size - 1;

        if (index <= limit / 2) {
            p = beginMarker;
            while (cnt < index) {
                cnt++;
                p = p.next;
            }
            return p;

        } else {
            p = endMarker;
            while (cnt < index) {
                cnt++;
                p = p.prefix;
            }
            return p;
        }
    }


    private T deleteEndNode() {

        Node<T> node = this.endMarker;
        Node<T> prefix = node.prefix;
        T value = node.value;
        if (prefix != null) prefix.next = null;
        node.prefix = null;
        endMarker = prefix;
        return value;

    }

    private boolean insertNode(int index, Node<T> node) {

        if (size == 0) {
            beginMarker = node;
            endMarker = node;
            return true;
        }

        if (index > 0 && index < size - 1) {
            Node<T> nextNode = getNode(index);
            Node<T> preNode = nextNode.prefix;
            preNode.next = node;
            node.prefix = preNode;
            nextNode.prefix = node;
            node.next = nextNode;
            return true;
        }

        if (index == 0) {
            beginMarker.prefix = node;
            node.next = beginMarker;
            beginMarker = node;
            return true;

        }

        if (index == size) {
            endMarker.next = node;
            node.prefix = endMarker;
            endMarker = node;
            return true;
        }

        return false;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new ArrayIndexOutOfBoundsException();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void push(T val) {
        this.insertNode(size, new Node<T>(val));
        size++;
    }

    public T pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        T value = this.deleteEndNode();
        size--;
        return value;
    }

    public T peek() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        return endMarker.value;
    }

    public int size() {
        return size;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator<T> iterator = iterator();
        sb.append('[');
        while (iterator.hasNext()) {
            sb.append(iterator.next());
            sb.append(',');
        }
        sb.delete(sb.length() - 1, sb.length());
        sb.append(']');
        return sb.toString();
    }

    /**
     * 迭代器接口
     */
    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }


    private class LinkedListIterator implements java.util.Iterator<T> {
        Node<T> current = beginMarker;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            T nextItem = current.value;
            current = current.next;
            return nextItem;
        }

        @Override
        public void remove() {
            LinkedStack.this.pop();
        }
    }
}
