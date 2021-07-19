package _算法._查找与排序2;


import java.util.Arrays;
import java.util.Scanner;

/***
 *
 * 用户输入海量数据，结束之后升序输出TopK。
 *
 * 思路：采用最小堆，时间复杂度为 NlogK
 *
 * api：PriorityQueue(Comparator.reverseOrder());
 *
 * ***/
public class _TopK与最小堆 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        int[] arr = new int[k];//topk
        int count = 0;
        int value = sc.nextInt();
        while (value != -1) {
            deal(value, arr, count, k);
            value = sc.nextInt();
            count++;
        }
        Arrays.sort(arr);
        for (int m : arr) {
            System.out.print(m+" ");
        }
    }

    private static void deal(int value, int[] arr, int count, int k) {
        if (count < k) {//输入数小于k，直接加入
            arr[count] = value;
            adjust_heap(arr, 0, count);
            return;
        }
        if (value <= arr[0]) return;//如果比堆顶小
        arr[0] = value;
        adjust_heap(arr, 0, k - 1);
    }

    private static void adjust_heap(int[] arr, int i, int end) {
        int left = 2 * i + 1;//左节点
        int right = 2 * i + 2;//右节点

        if (left > end) return;//出口
        if (right <= end) {//有两子节点时
            //选最小元素的下标
            int min = arr[i] > arr[left] ? left : i;
            min = arr[min] > arr[right] ? right : min;
            if (min == i) return;//不用调整了

            //交换
            int temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
            if (min == left) adjust_heap(arr, left, end);
            else adjust_heap(arr, right, end);

        } else {//只有一个节点
            int min = arr[i] > arr[left] ? left : i;
            if (min == i) return;

            //交换
            int temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
        }
    }
}
