package _LeetCode_HotCode_Set;

/**
 * @program: algorithm_by_java
 * @author: 一树
 * @data: 2020/12/23 15:22
 */
public class _高楼水杯 {
    public static void main(String[] args) {
        f(2,100);

    }

    public static void f(int k, int h) {
        int[][] dp = new int[k + 1][h + 1];
        //dp[鸡蛋数][测试次数]->找出最大高度

        int m = 0;//测试次数

        while (dp[k][m] < h) {
            m++;
            for (int i = 1; i <= k; i++) {
                dp[i][m] = dp[i][m - 1] + dp[i - 1][m - 1] + 1;
            }
        }
        System.out.println(m);
    }
}
