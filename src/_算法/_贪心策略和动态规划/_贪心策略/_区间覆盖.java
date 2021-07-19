package _算法._贪心策略和动态规划._贪心策略;


import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Math.max;

/***
 * 给定一个线段，n个区间，求用最少的区间覆盖线段
 *
 *策略：
 *
 * start<=l_start时找end最大的 then 更新l_start
 * if disappear:断点 then return wrong
 * if end>=l_end then return ans
 * else continue
 *
 * ***/
public class _区间覆盖 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();//区间数
        int x = sc.nextInt();//线段起点
        int y = sc.nextInt();//线段终点

        job2[] jobs = new job2[n];

        for (int i = 0; i < n; i++) {
            int o1 = sc.nextInt();
            int o2 = sc.nextInt();
            jobs[i] = new job2(o1, o2);
        }

        int ans = findway(jobs, x, y);
        System.out.println(ans);
    }

    private static int findway(job2[] jobs, int start, int end) {
        Arrays.sort(jobs);

        int cnt = 0;
        int end_max = 0;
        if (jobs[0].start > start) return -1;//这种情况无解
        end_max = jobs[0].end;
        for (int i = 1; i < jobs.length; i++) {
            //起点判断
            //尽量找end远的
            if (jobs[i].start > start) {
                //说明jobs[i-1]是start前最后一个
                cnt++;//使用区间+1
                start = jobs[i - 1].start;//更新线段起点
                end_max = max(jobs[i - 1].end, end_max);//记录一下之前能达的最长end
            }

            //断点判断
            if (jobs[i].start > jobs[i - 1].end) {
                return -1;//此情况不可能
            }

            //到达终点判断
            if (end_max >= end) {
                return cnt;//用的之前的区间，cnt不用++
            }
            if (jobs[i].end >= end){
                cnt++;//新增区间
                return cnt;
            }
        }
        //遍历完后未到达终点，则无解
        return -1;
    }
}


class job2 implements Comparable<job2> {

    public int start;
    public int end;
    public int point;//区间选点使用

    public job2(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public job2(int start, int end, int point) {
        this.start = start;
        this.end = end;
        this.point = point;
    }

    @Override
    public int compareTo(job2 o) {
        //按区间起点排序
        //起点相同，按end长度排序
        if (start != o.start) {
            return start - o.start;
        }
        return end - o.end;
    }
}
