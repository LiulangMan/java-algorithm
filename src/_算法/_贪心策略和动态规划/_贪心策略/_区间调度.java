package _算法._贪心策略和动态规划._贪心策略;


import java.util.Arrays;
import java.util.Scanner;

/***
 * 有n项工作，开始时间和结束时间分别是si和ti，同一时间只能做一项工作，即便是开始时间和结束时间重叠也不可，
 *
 * 你的目标是尽可能参与多的工作，那么最多可以参加多少工作呢？
 *
 *
 * 输入：
 * n
 * n个数 (开始时间)
 * n个数（结束时间）
 *
 * 输出：
 * 最多参加的工作数目
 *
 *
 * 策略：
 *
 * 1， 开始时间最早的？ no
 * 2， 工作时间最短的？ no
 * 3， 结束时间最短的？ <(￣︶￣)↗ yes
 * ***/
public class _区间调度 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        job[] jobs = new job[n];
        for (int i = 0; i < n; i++) {
            int start = sc.nextInt();
            jobs[i] = new job(start, 0);
        }

        for (int i = 0; i < n; i++) {
            jobs[i].end = sc.nextInt();
        }
        sc.close();

        int ans = findway(jobs);
        System.out.println(ans);

    }

    private static int findway(job[] jobs) {
        Arrays.sort(jobs);//对end排序
        //选取结束时间最早的
        int cnt = 1;
        int end = jobs[0].end;
        for (int i = 1; i < jobs.length; i++) {
            if (end < jobs[i].start) {
                cnt++;
                end = jobs[i].end;
            }
        }
        return cnt;
    }
}

class job implements Comparable<job> {

    public int start;
    public int end;
    public int point;//区间选点使用

    public job(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public job(int start, int end, int point) {
        this.start = start;
        this.end = end;
        this.point = point;
    }

    @Override
    public int compareTo(job o) {
        if (end != o.end) {
            return end - o.end;
        }
        return start - o.start;
    }
}

