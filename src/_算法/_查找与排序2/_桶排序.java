package _算法._查找与排序2;


import java.util.Arrays;

/***
 *
 * 创建桶，个数为n（数组长度），通过某算法，将数组元素入桶，如何取出。
 *
 *
 * **/

//创建链表
class Link {
    private Link next = null;//下一指针
    private int value = 0;

    Link(int value) {
        this.value = value;
    }

    Link() {
        //无参构造方法,head
    }

    void insertNextKeepSort(Link next) {
        //插入新元，且保证有序
        Link t = this;//如果head后没有元素
        if (t.next == null) {
            t.next = next;
            return;
        }
        Link p = t;//前指针
        t = t.next;
        while (t.value > next.value && (t.next != null)) {
            p = t;
            t = t.next;
        }
        if ((t.next == null) && (t.value > next.value)) {
            t.next = next;//最后了
            return;
        }
        p.next = next;
        next.next = t;

    }

    int popValue() {
        //出元
        Link t = this;
        Link p = this;
        while (t.next != null) {
            p = t;
            t = t.next;

        }//移到最后一个

        int temp = t.value;
        p.next = null;//释放
        return temp;
    }

    boolean hasNext() {
        //如果桶内还有元素
        return this.next != null;
    }
}

public class _桶排序 {
    public static void main(String[] args) {
        int[] arr = {234, 321, 546, 381, 455, 377, 624, 500};
        int[] brr = {234, 321, 546, 381, 455, 377, 624, 500};//对比
        Arrays.sort(brr);//对比
        tSort(arr);
        for (int m : arr) {
            System.out.print(m + " ");
        }
        System.out.println();
        for (int m : brr) {
            System.out.print(m + " ");
        }
    }

    private static void tSort(int[] arr) {
        int max = max(arr);
        Link[] link = new Link[arr.length + 1];
        for (int i = 0; i < link.length; i++) {
            link[i] = new Link();
        }
        int index;
        for (int m : arr) {
            index = m * arr.length / (max + 1);//分桶
            link[index].insertNextKeepSort(new Link(m));
        }
        int k = 0;
        for (Link m : link) {
            while (m.hasNext()) {
                arr[k++] = m.popValue();
            }
        }
    }

    private static int max(int[] arr) {
        int max = 0;
        for (int m : arr) {
            if (m > max) max = m;
        }
        return max;
    }
}
