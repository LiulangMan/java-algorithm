package _算法._贪心策略和动态规划._贪心策略;


import java.util.Scanner;

import static java.lang.Math.min;

/***
 * 问题描述：
 *
 * 有n个人渡河，其中有一艘船，每次最多只能坐两人（船需要被划回来），每人有不同的划船速度，每次渡河的时间取决于最慢的一个；
 *
 * 如果给定一种情况，求全部渡河的最短时间
 *
 * 如
 * 输入
 * 1 2 5 10
 *
 *
 * 输出：17
 *
 * 解释
 * 1 2 ->  cost 2
 * 1   <-  cost 1
 * 5 10 -> cost 10
 * 2 <-    cost 2
 * 1 2 ->  cost 2
 *
 *
 * 有一种直觉，其一是
 * 最快的送每一个人：
 * 假设有 a b c d
 * 则
 * a b
 * a
 * a c
 * a
 * a d
 * cost1 = b+a+c+a+d = 2a+b+c+d
 *
 *
 * 其二：
 *如果两岸都保留一个最快和次快的
 * 则
 * a b
 * a
 * c d
 * b
 * a b
 * cost2 = b+a+d+b+b = a+3b+d
 *
 * cost1 - cost2 = a+c-2b
 * 对于人数大于4的情况，重复上述过程就好
 * 所以只要判断 a+c 与 2b 谁更小即可
 *
 * ***/
public class _划船问题 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] speed = new int[n];
        for (int i = 0; i < n; i++) {
            speed[i] = sc.nextInt();
        }
        sc.close();

        int ans = findway(speed, n - 1);
        System.out.println(ans);


    }

    private static int findway(int[] speed, int n) {

        if (n == 0) return speed[0];//1个人
        if (n == 1) return speed[1];//2个人
        if (n == 2) return speed[0] + speed[1] + speed[2];//三个人
        else {
            //大于等于四个人的情况
            //1 3出发 1返回 1 4出发 1 返回
            int cost1 = 2 * speed[0] + speed[n - 1] + speed[n]; //送走最慢两人
            // 1 2 出发 1 返回 3 4 出发 2 返回
            int cost2 = speed[0] + 2 * speed[1] + speed[n];
            return min(cost1, cost2) + findway(speed, n - 2);
        }
    }
}
