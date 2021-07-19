package _算法._贪心策略和动态规划._动态规划;

import java.util.Scanner;

import static java.lang.Math.max;

/***

 给定一个三角形，例如下图：
 7
 3 8
 8 1 0
 2 7 4 4
 4 5 2 6 5

 现在从顶点往下遍历，每次只能选择左路或者右路，求路径之和的最大值


 dfs思路：自上而下的dfs，可增加记忆数组实现剪枝
 dp思路: 自下而上的DP，每次更新以（i，j）为顶点的三角形遍历最大值

 例如，上图中最后一行 4 5 2 6 5
 那么DP后，倒数两行为：
 7  12 10 10
 4  5  2  6  5

 以此类推
 * ***/
public class _数字三角形 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 三角形行数
        int[][] tri = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                tri[i][j] = sc.nextInt();
            }
        }
        sc.close();

        int ans = dp2(tri);
        System.out.println(ans);
    }

    private static int dp(int[][] triangle) {
        //dp[i][j]表示以i，j为顶点的路径最大和
        //由于dp过程只与后一行有关，为了节约空间，可以做成滚动数列的形式 row = 1 - row 实现 0 1 行滚动
        //另外也可以以覆盖方式优化,
        int row = 0;
        int[][] dp = new int[2][triangle.length];

        //初始化最后一行
        //就一个元素，路径之和即本身
        for (int i = 0; i < triangle.length; i++) {
            dp[row][i] = triangle[triangle.length - 1][i];
        }
        //从倒数第二行开始向上DP
        row = 1 - row;
        for (int i = triangle.length - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                int left = dp[1 - row][j]; //左分路
                int right = dp[1 - row][j + 1]; // 右分路
                dp[row][j] = triangle[i][j] + max(left, right);
            }
            //滚动
            row = 1 - row;
        }
        return dp[1 - row][0];
    }


    private static int dp2(int[][] triangle) {
        //dp[i][j]表示以i，j为顶点的路径最大和
        //由于dp过程只与后一行有关，为了节约空间，可以做成滚动数列的形式 row = 1 - row 实现 0 1 行滚动
        //以覆盖方式优化,
        int[] dp = new int[triangle.length];

        //初始化最后一行
        //就一个元素，路径之和即本身
        for (int i = 0; i < triangle.length; i++) {
            dp[i] = triangle[triangle.length - 1][i];
        }
        //从倒数第二行开始向上DP

        for (int i = triangle.length - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                int left = dp[j]; //左分路
                int right = dp[j + 1]; // 右分路
                //覆盖
                dp[j] = triangle[i][j] + max(left, right);
            }
        }
        return dp[0];
    }
}
