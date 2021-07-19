package _算法._位算法;

import java.util.Scanner;

/*
 * 算法1：削掉最低位的1   x = (x-1)&x
 * 算法2：移位>>>
 * 延伸语句  判断是否是2的整数次： if( ((x-1)&x) == 0)
 */
public class _二进制1的位数 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        //int ans = solve1(N);
        int ans = solve2(N);
        System.out.println(ans);
    }

    private static int solve2(int n) {
        int count = 0;
        while (n != 0) {
            n = (n - 1) & n;
            count++;
        }
        return count;
    }

    private static int solve1(int n) {
        int count = 0;

        while (n != 0) {
            if ((n & 1) == 1) count++;  //必须加括号
            n >>>= 1;
        }
        return count;
    }
}
