package _算法._递归_DFS_回溯_剪枝;


import java.util.ArrayList;
import java.util.Scanner;

import static _算法._递归_DFS_回溯_剪枝._返回所有子集的二进制解法.pow;

/***
 * 给定数组arr , 给定k  ，是否存在arr的子集，使得子集和等于k
 *
 * 此题就是 返回所有子集之解法 的实例
 *
 * ***/
public class _部分和 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        long k = sc.nextLong();

        System.out.println(findway(arr, k));

    }

    private static boolean findway(int[] arr, long k) {
        //二进制解法：
        ArrayList<Integer> list = new ArrayList<>();

        int n = arr.length;
        int forLen = pow(2, n) - 1;

        for (int i = 1; i <= forLen; i++) {
            for (int j = n - 1; j >= 0; j--) {
                if (((i >> j) & 1) == 1) {
                    list.add(arr[j]);
                }
            }

            int sum = 0;
            for (int j = 0; j < list.size(); j++) {
                sum += list.get(j);
            }
            if (sum == k)return true;

            list.clear();//回溯
        }
        return false;
    }
}
