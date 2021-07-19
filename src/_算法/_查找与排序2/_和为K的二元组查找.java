package _算法._查找与排序2;

import java.util.ArrayList;

/***
 * 已排序好的数组，查找和为k的两项
 *
 * 思路：快排变体，两个指针左右扫描，比k大右指针减1，反之左指针加1
 * ***/
public class _和为K的二元组查找 {
    private static ArrayList<Integer[]> c = new ArrayList<>();

    public static void main(String[] args) {

        int[] arr = {-8, -4, -2, 0, 2, 4, 6, 8, 10};
        find(arr, 10);
        for (Integer[] m : c) {
            System.out.println(m[0]+" "+m[1]);
        }
    }

    private static void find(int[] arr, int k) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int p = arr[left] + arr[right];
            if (p == k) {
                Integer[] r = {arr[left], arr[right]};
                c.add(r);
                left++;
            } else if (p < k) left++;
            else right--;
        }
    }
}
