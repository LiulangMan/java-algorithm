package _算法._贪心策略和动态规划._动态规划;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Math.max;

/***
 * Serling 公司购买长钢条，将其切割成短钢条出售。切割工序本身没有成本支出。公司管理层希望知道最佳的切割方案。
 * 假定我们知道Serling 公司出售一段长为i英寸的钢条价格为pi。钢条的长度为整英寸。
 *
 * 长度：1 2 3  4  5  6  7  8  9  10
 * 价格：1 5 8 16 10 17 17 20 24 30
 *
 * 问题描述：给定一段长度为n英寸的钢条和一个价格表pi（i=1，2，3......），求切割钢条方案，使得销售收益最大
 * 注意：如果长度为n的钢条的价格pn足够大，最优解可能就是完全不需要切割。
 *
 * ***/
public class _钢条切割 {
    private static int[] price = {1, 5, 8, 16, 10, 17, 17, 20, 24, 30};//price[i] = pi (i是钢条长度);
    private static int[] rec;//记忆数组
    private static ArrayList<Integer> cut = new ArrayList<>();//记录切割方案

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        rec = new int[n + 1];
        Arrays.fill(rec, -1);

//        int ans = r(n);
//        System.out.println(ans);
        System.out.println(dp(n));

//        for (int i = 0; i < rec.length; i++) {
//            System.out.print(i + " " + rec[i]);
//        }
    }

    private static int dp(int n) {
        //n代表切割长度
        int[] dp = new int[n + 1];
        //长度为0，初始化为0
        dp[0] = 0;

        //递推
        for (int i = 1; i <= n; i++) {
            int v = 0;
            //切割
            for (int j = 1; j <= i; j++) {
                //切割成j和i-j长度
                int v1 = dp[j] + dp[i - j];
                if (i < 10) {
                    //如果切割长度<=10，直接查价格表
                    int v2 = max(price[i-1], v1);
                    //与之前的切割方案比较，取最大值
                    v = max(v, v2);
                } else {
                    //如果切割长度>10,没有价格表查询，只能切割成v1
                    v = max(v, v1);
                }
            }
            dp[i] = v;//i递推结束后，保存
        }
//        for (int i = 0; i < dp.length; i++) {
//            System.out.println(i + "--->" + dp[i] );
//        }
        return dp[n];//返回长度为n的结果
    }

    private static int r(int n) {
        //记忆型递归
        //n:钢条长度
        if (rec[n] >= 0) return rec[n];
        if (n <= 0) return 0;

        int ans = 0;
        for (int i = 1; i <= n; i++) {
            int v;
            if (i <= 10) {
                v = r(n - i) + price[i - 1];
            } else {
                v = r(i) + r(n - i);
            }
            ans = max(ans, v);
        }
        //记录
        rec[n] = ans;
        return ans;
    }
}
