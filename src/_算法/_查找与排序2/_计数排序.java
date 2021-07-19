package _算法._查找与排序2;

import java.util.Arrays;

/***
 *
 * 简知：牺牲空间换取时间的排序算法
 *
 *
 *
 * ***/
public class _计数排序 {
    public static void main(String[] args) {
        int[] arr = {2, 3, 1, 6, 3, 4, 9, 6, 4, 6, 1};
        countSort(arr);//升序排序
        for (int m : arr) {
            System.out.print(m + " ");
        }
    }

    private static void countSort(int[] arr) {
        int max = max(arr);
        int[] helper = new int[max + 1];
        Arrays.fill(helper,0);
        for (int m : arr) {
            helper[m]++;
        }
        for (int i = 0, k = 0; i < helper.length; ) {
            if (helper[i] == 0) {
                i++;
                continue;
            }
            arr[k++] = i;
            helper[i]--;
        }

    }

    private static int max(int[] arr) {
        int max = 0;
        for (int m : arr) {
            if (max < m) max = m;
        }
        return max;
    }
}

