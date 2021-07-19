package 笔试区._temp_diction3;

import java.util.Arrays;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int len = sc.nextInt();
        String[] srr = sc.next().split(",");
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = Integer.parseInt(srr[i]);
        }
        //超时
        //sort(arr, 0, arr.length - 1);

        Arrays.sort(arr);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (i != arr.length - 1) {
                sb.append(arr[i]);
                sb.append(',');
            } else {
                sb.append(arr[i]);
                System.out.print(sb.toString());
            }
        }

    }

    private static void sort(int[] arr, int left, int right) {

        //快速排序

        int lo = left;
        int hi = right;
        int p = arr[(left + right) >> 1];//哨兵

        while (lo < hi) {
            while (lo < hi && arr[hi] > p) hi--;
            while (lo < hi && arr[lo] < p) lo++;


            //swap
            if (lo != hi) {
                int temp = arr[lo];
                arr[lo] = arr[hi];
                arr[hi] = temp;
            }
        }
        if (left < lo - 1) sort(arr, left, lo - 1);
        if (right > lo + 1) sort(arr, lo + 1, right);
    }
}
