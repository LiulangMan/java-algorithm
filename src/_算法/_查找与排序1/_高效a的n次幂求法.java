package _算法._查找与排序1;

import java.util.Scanner;


/***
 *
 * 高效n次幂求法：倍增法
 *
 * ***/
public class _高效a的n次幂求法 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int n = sc.nextInt();


        long ans = pow(a, n);

        long a1 = pow0(a, n);

        long a2 = pow1(a, n);

        System.out.println(ans + " " + a1 + " " + a2);
    }

    private static long pow1(int a, int n) {
        //高校递归算法
        if (n == 0) return 1;
        long an = a;
        int res = 1;
        while ((res << 1) <= n) {
            an = an * an;
            res <<= 1;  //翻倍
        }

        //剩余n-an交给下一层
        return an * pow1(a, n - res);
    }

    private static long pow0(int a, int n) {
        //低效算法
        long ans = 1;
        if (a == 0) return 0;
        if (n == 0) return 1;
        for (int i = 0; i < n; i++) {
            ans = ans * a;
        }
        return ans;
    }

    private static long pow(int a, int n) {
        //高效迭代算法
        long ans = 1;
        if (a == 0) return 0;
        if (n == 0) return 1;
        while (n != 0) {
            if ((n & 1) == 1) {
                ans = ans * a;
            }
            n >>>= 1;
            a = a * a;
        }
        return ans;
    }
}
