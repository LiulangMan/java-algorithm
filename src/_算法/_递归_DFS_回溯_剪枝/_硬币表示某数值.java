package _算法._递归_DFS_回溯_剪枝;

import java.util.Scanner;

/**
 * 给定 1 5 10 25 的硬币，给定n，求硬币组合成n的方式的个数
 * 输入：n
 * 输出：表示个数的数字
 ***/
public class _硬币表示某数值 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(findway1(n));
        System.out.println(findway2(n));
    }

    private static long findway2(int n) {
        return CountCore(n, new int[]{1, 5, 10, 25}, 3);
    }

    private static long CountCore(int n, int[] arr, int cur) {
        if (cur == 0) return 1;//最小面额

        long res = 0;
        for (int i = 0; i * arr[cur] <= n; i++) {
            res += CountCore(n - i * arr[cur], arr, cur - 1);
        }
        return res;
    }

    private static long findway1(int n) {

        //类背包问题的解法
        //时间复杂度：O(n*m)
        int[][] dp = new int[4 + 1][n + 1];

        //辅助数组：行值转换
        int[] arr = new int[5];
        arr[1] = 1;
        arr[2] = 5;
        arr[3] = 10;
        arr[4] = 25;

        //用1组成的数只有一种
        for (int i = 1; i <= n; i++) {
            dp[1][i] = 1;
        }

        //组成0的硬币只有一种
        for (int i = 1; i <= 4; i++) {
            dp[i][0] = 1;
        }

        for (int i = 2; i <= 4; i++) {
            //此处为了节约空间，可改为滚动数列：row = 1 - row
            for (int j = 1; j <= n; j++) {
                if (arr[i] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - arr[i]];
                    //如果是背包问题，（金币不重复），此处改为 dp[i][j] = dp[i - 1][j] + dp[i - 1][j - arr[i]]
                }
            }
        }

        return dp[4][n];
    }
}
