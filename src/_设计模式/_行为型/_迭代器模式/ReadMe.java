package _设计模式._行为型._迭代器模式;

import java.util.Iterator;

/**
 * @program: algorithm_by_java
 * @author: 一树
 * @data: 2020/12/16 13:43
 */
public class ReadMe implements Iterable{
    @Override
    public Iterator iterator() {
        return new itr();
    }


    private static class itr implements Iterator {
        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Object next() {
            return null;
        }
    }
}
