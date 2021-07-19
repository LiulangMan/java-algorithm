package _算法._贪心策略和动态规划._贪心策略;


import java.util.Arrays;
import java.util.Scanner;

/***
 * 有n个人，每个人重wi，每艘船最多承重c，且只能坐两人
 * 求一种策略，使所有人过河，用船数最少
 *
 *
 * 策略：
 * 先找出最轻的人i，如果每个人都无法和他一起过河（重量和>c），那么他自己独一人过河
 * 否则，找满足条件的最重一人j和他过河
 * ***/

public class _乘船问题 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int c = sc.nextInt();
        int weight[] = new int[n];
        for (int i = 0; i < n; i++) {
            weight[i] = sc.nextInt();
        }

        int ans = findway(weight, c);
        System.out.println(ans);
    }

    private static int findway(int[] weight, int c) {
        Arrays.sort(weight);
        int cnt = 0;//用船数
        int right;//右指针
        int len_arr = weight.length - 1;//剩余数
        while (len_arr >= 0) {
            //找出满足条件的最重一人
            right = len_arr;
            while (right > 0 && weight[right] + weight[0] > c) {
                right--;
            }
            if (right > 0) {
                //将left 和 right 移除
                remove(weight, right, len_arr);
                remove(weight, 0, len_arr - 1);
                len_arr -= 2;
                cnt++;
            } else {
                //将left移除
                remove(weight, 0, len_arr);
                len_arr--;
                cnt++;
            }
        }
        return cnt;
    }

    private static void remove(int[] weight, int target, int len) {
        for (int i = target; i <= len - 1; i++) {
            weight[i] = weight[i + 1];
        }
    }
}
