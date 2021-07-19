package _数据结构._List;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @program: suanfa_by_java
 * @author: 一树
 * @data: 2020/11/15 10:34
 */
public class MyLinkedList<T> implements Iterable<T>{

    private int size;
    private Node<T> beginMarker;
    private Node<T> endMarker;

    public MyLinkedList() {
        doInit();
    }

    public MyLinkedList(Collection<? extends T> c) {
        doInit();
        for (T t : c) {
            add(t);
        }
    }

    private void doInit() {
        size = 0;
    }

    public void addAll(Collection<? extends T> others) {
        for (T other : others) {
            add(other);
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


    private boolean deleteNode(int index) {
        checkIndex(index);

        if (index > 0 && index < size - 1) {
            Node<T> node = getNode(index);
            Node<T> prefix = node.prefix;
            Node<T> next = node.next;
            prefix.next = next;
            next.prefix = prefix;
            node.next = null;
            node.prefix = null;
            return true;
        }

        if (index == 0) {
            Node<T> node = this.beginMarker;
            Node<T> next = node.next;
            next.prefix = null;
            beginMarker = next;
            node.next = null;
        }


        if (index == size - 1) {
            Node<T> node = this.endMarker;
            Node<T> prefix = node.prefix;
            prefix.next = null;
            node.prefix = null;
            endMarker = prefix;
            return true;
        }
        return false;
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


    public void add(T t) {
        this.insertNode(size, new Node<T>(t));
        size++;
    }


    public void remove(int index) {
        this.deleteNode(index);
        size--;
    }

    public T get(int index) {
        return getNode(index).value;
    }

    public T set(int index, T newValue) {
        Node<T> node = getNode(index);
        T old = node.value;
        node.value = newValue;
        return old;
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
            MyLinkedList.this.remove(MyLinkedList.this.size - 1);
        }
    }
}
