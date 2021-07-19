package 笔试区._temp;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[] high = new int[N + 1];
        high[0] = 0;
        for (int i = 1; i <= N; i++) {
            high[i] = sc.nextInt();
        }
        System.out.println(del(high));


    }

    private static int del(int[] high) {
        int length = high.length;
        float[] dp = new float[length];
        dp[length - 1] = 0;
        for (int i = length - 1; i > 0; i--) {
            dp[i - 1] = (dp[i] + high[i]) / 2;
        }

        float jk = dp[0] % (int) dp[0];
        float e = (float) 0.000001;

        return Math.abs(jk) > e ? (int) dp[0] + 1 : (int) dp[0];
    }

//    private static boolean game(int[] high, int E) {
//        for (int i = 1; i < high.length; i++) {
//            E += E - high[i];
//            if (E < 0) return false;
//        }
//
//        return true;
}
