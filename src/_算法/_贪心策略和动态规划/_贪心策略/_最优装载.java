package _算法._贪心策略和动态规划._贪心策略;

import java.util.Arrays;
import java.util.Scanner;

/***
 * 给出n个物体，第i个重wi，尽量选择多的物体，使总重量不超过C
 *
 * ***/
public class _最优装载 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int c = sc.nextInt();
        int[] bag = new int[n];
        for (int i = 0; i < n; i++) {
            bag[i] = sc.nextInt();
        }
        sc.close();

        int ans = findway(bag, c);
        System.out.println(ans);
    }

    private static int findway(int[] bag, int c) {
        Arrays.sort(bag);
        //很简单
        //每次都拿最轻的
        int cnt = 0;
        int sum = 0;
        for (int i = 0; i < bag.length; i++) {
            if (sum + bag[i] <= c) {
                sum += bag[i];
                cnt++;
            } else break;
        }
        return cnt;
    }
}

