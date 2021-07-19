package _数据结构._Test;

import _数据结构._List.MyArrayList;
import _数据结构._List.MyLinkedList;
import _数据结构._Map.MyHashMap;
import _数据结构._Queue.LinkedQueue;
import _数据结构._Set.MyHashSet;
import _数据结构._Stack.ArrayStack;
import _数据结构._Stack.LinkedStack;
import org.junit.Test;

import java.util.*;

/**
 * @program: suanfa_by_java
 * @author: 一树
 * @data: 2020/11/14 22:12
 */
public class TestCollection {
    @Test
    public void t1() {
        MyArrayList<Integer> list = new MyArrayList<>();

        list.add(1);
        list.add(2);
        list.add(3);

        list.remove(2);

        List<Integer> a = new ArrayList<>();
        a.add(5);
        a.add(6);

        list.addAll(a);
        list.set(1, 9);

        list.sort(((o1, o2) -> o1 - o2));
        System.out.println(list);

    }


    @Test
    public void t2() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        list.remove(0);

        List<Integer> others = new ArrayList<>();
        others.add(5);
        others.add(6);
        others.add(7);
        list.addAll(others);
        list.set(3, 1);

        System.out.println(list);

        for (Integer integer : list) {
            System.out.println(integer);
        }


    }

    @Test
    public void t3() {
        ArrayStack<Integer> stack = new ArrayStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }


    @Test
    public void t4() {
        LinkedStack<Integer> stack = new LinkedStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }


    @Test
    public void t5() {
        LinkedQueue<Integer> queue = new LinkedQueue<>();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }

    }


    @Test
    public void t6() {
        MyHashMap<String, Integer> map = new MyHashMap<>(5);
        map.put("1", 1);
        map.put("6", 6);
        map.put("5", 5);
        map.put("7", 7);
        map.put("32", 32);
        map.put("20", 20);
        map.put("21", 21);
        map.put("22", 22);
        map.put("23", 23);
        map.put("24", 24);
        map.put("25", 25);
        map.put("26", 26);
        map.put("27", 27);
        map.put("28", 28);
        map.put("29", 29);
        map.put("30", 30);
        map.put("31", 31);
        map.put("32", 32);


        for (MyHashMap.Node<String, Integer> node : map.enterSet()) {
            System.out.println(node.getKey() + ":" + node.getValue());
        }

        System.out.println(map.get("24"));
        System.out.println(map.get("25"));
        System.out.println(map.get("26"));
        System.out.println(map.get("27"));
        System.out.println(map.get("28"));
        System.out.println(map.get("6"));
    }

    @Test
    public void t7() {
        MyHashSet<Integer> set = new MyHashSet<>();
        set.add(1);
        set.add(1);
        set.add(12);
        set.add(13);
        set.add(14);
        set.add(12);
        System.out.println("size:" + set.size());
        for (Integer integer : set) {
            System.out.println(integer);
        }
    }
}
