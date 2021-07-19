package _设计模式._创建型._原型模式;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: algorithm_by_java
 * @author: 一树
 * @data: 2020/11/30 16:21
 */
@SuppressWarnings("all")
public class ProtoTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        ConcreteProtoTypeA a = new ConcreteProtoTypeA("123");
        //浅克隆
        ConcreteProtoTypeA clone = (ConcreteProtoTypeA) a.clone();
        //深克隆
        ConcreteProtoTypeA deepClone = (ConcreteProtoTypeA) a.deepClone();
        a.add(1);
        a.add(2);
        a.add(3);
        a.showList();
        clone.showList();
        deepClone.showList();
    }

    //抽象原型
    interface ProtoType<T> {
        T clone();
    }

    static class ConcreteProtoTypeB implements ProtoType<ConcreteProtoTypeB> {
        private String desc;
        private List<Integer> list;

        public ConcreteProtoTypeB(String desc) {
            this.desc = desc;
            list = new ArrayList<>();
        }

        public void add(int a) {
            list.add(a);
        }

        public void showList() {
            System.out.print(this.getClass().getSimpleName() + ":");
            for (Integer integer : list) {
                System.out.print(integer + " ");
            }
        }

        @Override
        public ConcreteProtoTypeB clone() {
            //这种通过new的方式介入了初始化过程，可以实现Cloneable直接复制class文件，除去加载过程
            return new ConcreteProtoTypeB(this.desc);
        }
    }

    //这里属性是复制引用，即浅克隆
    static class ConcreteProtoTypeA implements Cloneable, Serializable {
        private String desc;
        private List<Integer> list;

        public ConcreteProtoTypeA(String desc) {
            this.desc = desc;
            list = new ArrayList<>();
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            ConcreteProtoTypeA concreteProtoTypeA = null;
            try {
                concreteProtoTypeA = (ConcreteProtoTypeA) super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return concreteProtoTypeA;
        }

        //巧用序列化实现深克隆
        protected Object deepClone() {
            try {
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(bos);
                oos.writeObject(this);
                ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
                ObjectInputStream ois = new ObjectInputStream(bis);

                return ois.readObject();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        public void add(int a) {
            list.add(a);
        }

        public void showList() {
            System.out.print(this.getClass().getSimpleName() + ":");
            for (Integer integer : list) {
                System.out.print(integer + " ");
            }
            System.out.println();
        }

        @Override
        public String toString() {
            return "ConcreteProtoTypeA{" +
                    "desc='" + desc + '\'' +
                    '}';
        }
    }

}

