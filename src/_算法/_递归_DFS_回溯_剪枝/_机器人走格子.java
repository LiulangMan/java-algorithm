package _算法._递归_DFS_回溯_剪枝;

import java.util.Scanner;

/***
 * 一个机器人在 x*y 的格子上走，且只能向右或者向下走一格，如果从左上角走到右下角，问有多少种走法
 * 输入:x y
 * 输出：走法的个数
 *
 *数学模型：
 * f(1,1) = f(1,2) = f(2,1) = 1
 * f（x,y） = f(x-1,y) + f(x,y-1)
 * ***/
public class _机器人走格子 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();
        System.out.println(dp(x, y));
        System.out.println(findway(x, y));
    }

    private static long findway(int x, int y) {
        if (x == 1 || y == 1) return 1;
        return findway(x - 1, y) + findway(x, y - 1);
    }
    private static long dp(int x,int y){
        if (x == 1 || y == 1)return 1;
        long[][] dp = new long[x+1][y+1];
        for (int i = 1; i <= y; i++) {
            dp[1][i] = 1;
        }
        for (int i = 1; i <= x; i++) {
            dp[i][1] = 1;
        }

        for (int i = 2; i <= x ; i++) {
            for (int j = 2; j <=y ; j++) {
                dp[i][j] = dp[i-1][j]+dp[i][j-1];
            }
        }
        return dp[x][y];
    }
}
