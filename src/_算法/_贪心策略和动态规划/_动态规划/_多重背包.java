package _算法._贪心策略和动态规划._动态规划;

import static java.lang.Math.max;

public class _多重背包 {

    private static int[] w = {2, 4, 3, 2};//物品重量
    private static int[] v = {2, 5, 4, 1};//物品价值
    private static int[] m = {5, 5, 5, 5};//物品数量
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
                    for (int k = 1; k <= j / w[i] && k <= m[i]; k++) {   //比完全背包多了个 k<=m[i]
                        v2 = Math.max(v2, dp[i][j - k * w[i]] + k * v[i]);
                    }
                    dp[i][j] = max(v1, v2);
                }
            }
        }
        return dp[l - 1][W];
    }
}
