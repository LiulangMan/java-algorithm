package 笔试区._temp;

import java.util.*;

public class Main2 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int[] arr = new int[100];

        System.out.println(f(arr, m));
    }

    private static int f(int[] arr, int m) {
        Arrays.sort(arr);
        int[][] dp = new int[arr.length][m + 1];

        //初始化
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 0;
        }

        //初始化第一行，能装就装
        for (int i = 1; i <= m; i++) {
            if (i >= arr[0]) {
                dp[0][i] = arr[0];
            }
        }

        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= m; j++) {
                if (arr[i] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    //放得进去
                    int v1 = dp[i - 1][j];//不放
                    int v2 = dp[i - 1][j - arr[i]] + arr[i];//放
                    dp[i][j] = Math.max(v1, v2);
                }

            }
        }
        return dp[arr.length - 1][m];
    }
}
