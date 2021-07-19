package _算法._贪心策略和动态规划._动态规划;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Math.max;

/***
 * 01背包：
 *
 * 有n个重量和价值分别为wi，vi的物品，从这些物品中挑选出总重量不超过W的物品（区别一部分），每个物品仅可以拿一次
 * 求所有挑选方案中价值总和的最大值
 *
 *
 2 3
 1 2
 3 4
 2 2
 *
 * ***/

public class _背包问题 {
    private static int[] w;//重量
    private static int[] v;//价值
    private static int l;//物品个数
    private static int W;//背包称重
    private static int[][] rec;//递归记忆

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        l = 4;//这里可以改变数据
        W = 5;
        w = new int[l];
        v = new int[l];
        rec = new int[l][W + 1];

        for (int i = 0; i < l; i++) {
            w[i] = sc.nextInt();
            v[i] = sc.nextInt();
            Arrays.fill(rec[i], -1);//初始化
        }
        sc.close();

        int remain = W;
        int ans = dfs(0, remain);
        System.out.println(ans);
        System.out.println(dp());

    }

    /***
     *
     * dfs:O(t) = 2^n
     *
     * ***/
    private static int dfs(int i, int remain) {
        if (W <= 0) return 0;
        if (i == l) return 0;//没有物品

        //计算之前先查询
        if (rec[i][remain] >= 0) {
            return rec[i][remain];
        }

        //不选择i物品
        int v1 = dfs(i + 1, remain);

        //选择i物品
        int result;
        if (w[i] <= remain) {
            int v2 = v[i] + dfs(i + 1, remain - w[i]);
            result = max(v1, v2);
        } else {
            result = v1;
        }

        //计算之后做记录
        rec[i][remain] = result;

        return result;
    }

    private static int dp() {
        //l:选择范围
        //W：背包重量
        int[][] dp = new int[l][W + 1];
        //初始化：当背包为0时，什么都装不进去
        for (int i = 0; i < l; i++) {
            dp[i][0] = 0;
        }
        //初始化:对于只有一种物品的时候，能装就装
        for (int i = 0; i <= W ; i++) {
            if (w[0]<=i){
                dp[0][i] = v[0];
            }else {
                dp[0][i] = 0;
            }
        }

        //dp迭代
        for (int i = 1; i < l; i++) {
            for (int j = 1; j < W + 1; j++) {
                if (w[i] > j) {
                    dp[i][j] = dp[i - 1][j];//如果i物品放不进去，价值维持上一状态
                } else {
                    //要么不放，价值维持上一状态
                    //或者放，然后在dp[i - 1][j - w[i]] + v[i]找最优选择
                    //两者取最大值
                    dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - w[i]] + v[i]);
                }
            }
        }
        return dp[l - 1][W];
    }
}
