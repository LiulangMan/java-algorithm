package _算法._递归_DFS_回溯_剪枝;

import java.util.Scanner;

/***
 *
 * 一个小孩走n阶楼梯，一次可以走一阶、两阶、三阶，问：走完楼梯有多少种走法，结果 Mod 1000000007
 *
 * 数学模型：
 * f（1） = 1
 * f（2） = 2
 * f（3） = 4
 * f（n) = f（n-1）+f（n-2）+f（n-3）  （n>3）
 *
 *
 * 此题可用类似于斐波拉契快速算法求解：
 *
 *                    [1,1,0]  ^ n
 * [f(3),f(2),f(1)] * [1,0,1]       =  [f(n+3),f(n+2),f(n+1)]
 *                    [1,0,0]
 *
 *
 * 时间复杂度：O(log n)
 * ***/
public class _上楼梯 {
    private static int ans;
    private static final int Mod = 1000000007;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
//        findway(n);
//        ans %= Mod;
        System.out.println(findway2(n));
    }

    private static void findway(int n) {

//        时间复杂度：O(3^n)
        if (n == 0) {
            ans++;
            return;
        }
        if (n < 0) return;

        findway(n - 1);//走一步
        findway(n - 2);//走两步
        findway(n - 3);//走三步

    }

    private static long findway2(int n) {
//        时间复杂度：O(n)
        if (n < 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;
        if (n == 3) return 4;
        long x1 = 1;
        long x2 = 2;
        long x3 = 4;
        long x1_1;
        for (int i = 4; i <= n; i++) {
            x1_1 = x1;
            x1 = x2;
            x2 = x3;
            x3 = (x1_1 + x1 + x2) % Mod;
        }
        return x3;
    }
}
