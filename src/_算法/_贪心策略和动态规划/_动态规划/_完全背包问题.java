package _算法._贪心策略和动态规划._动态规划;

import static java.lang.Math.max;

/***
 * 01背包：
 *
 * 有n个重量和价值分别为wi，vi的物品，从这些物品中挑选出总重量不超过W的物品（区别一部分），每个物品可以拿多次
 * 求所有挑选方案中价值总和的最大值
 *
 *
 2 3
 1 2
 3 4
 2 2
 *
 * ***/
public class _完全背包问题 {
    private static int[] w = {2, 4, 3, 2};//物品重量
    private static int[] v = {2, 5, 4, 1};//物品价值
    private static int W = 20;
    private static int l = 4;

    public static void main(String[] args) {
        System.out.println(dp());
    }

    private static int dp() {
        int[][] dp = new int[l][W + 1];

        //初始化第一行
        for (int i = 0; i <= W; i++) {
            //因为可以多次拿，求拿的次数
            dp[0][i] = i / w[0] * v[0];
        }

        for (int i = 1; i < l; i++) {
            for (int j = 0; j <= W; j++) {
                if (w[i] > j) {
                    //拿不下，继承上一行
                    dp[i][j] = dp[i - 1][j];
                } else {
                    int v1 = dp[i - 1][j];//不拿这一行

                    //拿这一行：可以拿多个，因此再内嵌一个循环
                    int v2 = 0;
                    for (int k = 1; k <= j / w[i]; k++) {
                        v2 = Math.max(v2, dp[i][j - k * w[i]] + k * v[i]);
                    }
                    // int v2 = dp[i][j - w[i]] + v[i];//拿这一行，拿了还可以拿，因此i不减
                    dp[i][j] = max(v1, v2);
                }
            }
        }
        return dp[l - 1][W];
    }
}
