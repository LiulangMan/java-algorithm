package _算法._贪心策略和动态规划._贪心策略;


import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Math.min;

/***
 * 有n个物体，第i个重量为wi，价值为vi，在重量不超过c的条件下
 * 每一个物体可以取走一部分，价值按取走的比例计算
 * 求最大价值
 *
 * ***/
public class _部分背包 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int c = sc.nextInt();
        thing[] things = new thing[n];
        for (int i = 0; i < n; i++) {
            int weight = sc.nextInt();
            int value = sc.nextInt();
            things[i] = new thing(weight, value);
        }
        sc.close();

        double ans = findway(things, c);
        System.out.println(ans);
    }

    private static double findway(thing[] things, int c) {
        Arrays.sort(things);//按价值排序
        double v = 0;
        int now_c = 0;
        for (int i = things.length - 1; i >= 0; i--) {
            //从高价值拿走
            int take = min(things[i].weight, c - now_c);
            if (now_c + take <= c) {
                now_c += take;
                v += take == things[i].weight ? things[i].value : (double) (c - now_c) / things[i].weight * things[i].value;
            } else break;

        }
        return v;
    }
}


class thing implements Comparable<thing> {
    public int weight;
    public int value;

    thing(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }

    @Override
    public int compareTo(thing o) {
        return value - o.value;
    }
}
