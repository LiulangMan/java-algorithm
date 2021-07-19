package 笔试区._temp_diction3;

import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String in = sc.next();
        int m = Integer.parseInt(in.split(",")[0]);
        int n = Integer.parseInt(in.split(",")[1]);
        int[][] arr = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        System.out.println(f(arr, m - 1, n - 1));

    }

    private static int f(int[][] arr, int m, int n) {
        //出口
        int[][] dp = new int[m + 1][n + 1];

        dp[m][n] = arr[m][n];
        //初始化边
        for (int i = m - 1; i >= 0; i--) {
            dp[i][n] = arr[i][n] + dp[i + 1][n];
        }

        for (int i = n - 1; i >= 0; i--) {
            dp[m][i] = arr[m][i] + dp[m][i + 1];
        }

        //迭代
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                dp[i][j] = arr[i][j] + Math.max(dp[i + 1][j], dp[i][j + 1]);
            }
        }
        return dp[0][0];
    }
}
