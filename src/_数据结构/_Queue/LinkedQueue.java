package _数据结构._Queue;

import java.util.EmptyStackException;

/**
 * @program: suanfa_by_java
 * @author: 一树
 * @data: 2020/11/15 15:10
 */
public class LinkedQueue<T> {

    private Node<T> first;
    private Node<T> end;
    private int size;

    private class Node<T> {
        T val;
        Node<T> next;
        Node<T> pre;

        Node() {
        }

        Node(T val) {
            this.val = val;
        }
    }

    public LinkedQueue() {
        doInit();
    }

    private void doInit() {
        size = 0;
        first = new Node<>();
        end = new Node<>();
        first.next = end;
        end.pre = first;
    }

    public boolean offer(T val) {
        Node<T> node = new Node<>(val);
        Node<T> pre = end.pre;
        pre.next = node;
        node.pre = pre;
        node.next = end;
        end.pre = node;
        size++;
        return true;
    }

    public T poll() {
        if (size == 0) {
            throw new EmptyStackException();
        }

        Node<T> t = first.next;
        T val = t.val;
        t.next.pre = first;
        first.next = t.next;
        t.next = null;
        t.pre = null;
        size--;
        return val;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T peek() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        return first.next.val;
    }
}
