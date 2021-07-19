package _算法._贪心策略和动态规划._贪心策略;


import java.util.Scanner;

import static java.lang.Math.max;
import static java.lang.Math.min;

/***
 * 所谓贪心策略，即当时做出的决策对当前最有利
 *
 *
 * 题：
 * 有 1 5 10 25 50 的硬币 各诺干
 *
 * 输入：n
 * 接下来5 个 整数 ，代表上述硬币对应的个数（从小到大）
 * 输出  m （用硬币组成n的最少个数）
 *
 *
 *
 * 显然，应该每次都用最大的币值
 *
 *  ***/
public class _硬币支付 {

    private final static int[] coin = {1, 5, 10, 25, 50};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[5];


        for (int i = 0; i < 5; i++) {
            arr[i] = sc.nextInt();
        }
        sc.close();

        int ans = findway(arr, 4, n);
        System.out.println(max(ans, 0));
    }

    private static int findway(int[] arr, int cur, int money) {
        if (cur == -1) {
            return Integer.MIN_VALUE;
        }
        if (money == 0) {
            return 0;
        }

        int need = money / coin[cur];
        int cnt = min(need, arr[cur]);
        int remain = money - coin[cur] * cnt;

        return findway(arr, cur - 1, remain) + cnt;
    }
}
