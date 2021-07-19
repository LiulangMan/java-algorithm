package 笔试区._temp_diction5;

import java.util.Scanner;

public class Main2 {
    //public static int cnt = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int mod = 1000000007;
        int[] dp = new int[n];

        for (int i = 1; i < n; i++) {
            int cnt = 0;
            for (int j = 1; j <= m && i-j >=0; j++) {
                cnt += dp[i-j];
                cnt %= mod;
            }
            dp[i] = cnt;
        }

        System.out.println(dp[n-1]);

    }
}
