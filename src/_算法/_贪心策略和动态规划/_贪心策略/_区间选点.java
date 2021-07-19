package _算法._贪心策略和动态规划._贪心策略;

import java.util.Arrays;
import java.util.Scanner;

/***
 * 有n个区间（可重叠，可交叉）
 * 如果在区间内选了一个点，则称为命中该区间
 * 求出最少的点的数目，可以命中所有区间
 *
 * 变体：每个区间有点数某种要求
 *
 5
 start end point
 3 7 3
 8 10 3
 6 8 1
 1 3 1
 10 11 1
 *
 *
 * ***/
public class _区间选点 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        job[] jobs = new job[n];

        for (int i = 0; i < n; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            int point = sc.nextInt();
            jobs[i] = new job(start, end, point);
        }

        sc.close();

        int ans = findway(jobs);
        System.out.println(ans);
    }

    private static int findway(job[] jobs) {
        Arrays.sort(jobs);//按区间尾排序
        int cnt = jobs[0].point; // 第一个区间必须的投点数
        int end = jobs[0].end;
        for (int i = 1; i < jobs.length; i++) {
            if (end < jobs[i].start) {
                end = jobs[i].end; //end记录的是非独立区的end，一旦end更新，表示与前面的区间没有重叠和交叉
                cnt += jobs[i].point;//在该区中，需要某种的点数
            } else {
                //非独立，只与前一个区间的点数相关
                if (jobs[i - 1].point < jobs[i].point) {
                    cnt += jobs[i].point - jobs[i - 1].point;
                    //交叉的区间，也得更新 end
                    end = jobs[i].end;
                }
            }
        }
        return cnt;
    }
}

