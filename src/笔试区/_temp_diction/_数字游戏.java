package 笔试区._temp_diction;

import java.util.Scanner;

//问题描述
//        　　栋栋正在和同学们玩一个数字游戏。
//
//        　　游戏的规则是这样的：栋栋和同学们一共n个人围坐在一圈。栋栋首先说出数字1。接下来，坐在栋栋左手边的同学要说下一个数字2。再下面的一个同学要从上一个同学说的数字往下数两个数说出来，也就是说4。下一个同学要往下数三个数，说7。依次类推。
//
//        　　为了使数字不至于太大，栋栋和同学们约定，当在心中数到 k-1 时，下一个数字从0开始数。例如，当k=13时，栋栋和同学们报出的前几个数依次为：
//        　　1, 2, 4, 7, 11, 3, 9, 3, 11, 7。
//
//        　　游戏进行了一会儿，栋栋想知道，到目前为止，他所有说出的数字的总和是多少。
//        输入格式
//        　　输入的第一行包含三个整数 n,k,T，其中 n 和 k 的意义如上面所述，T 表示到目前为止栋栋一共说出的数字个数。
//        输出格式
//        　　输出一行，包含一个整数，表示栋栋说出所有数的和。
//        样例输入
//        3 13 3
//        样例输出
//        17
public class _数字游戏 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();//人数
        int k = sc.nextInt();//最大的数
        int t = sc.nextInt();//东东说的数字数
        //long ans = solve1(n, k, t);
        long ans = solve2(n, k, t);
        System.out.println(ans);
    }

    private static long solve2(int n, int k, int t) {
        long sum = 0;
        int cnt = 0;
        int x = 0;
        int start;
        int end;
        while (cnt < t) {
            //x = (1 + (1 + cnt * n)) * (1 + cnt * n) / 2;
            end = (1 + cnt * n);
            start = cnt - 1 >= 0 ? (1 + (cnt - 1) * n) : 0;
            for (int i = start; i <= end; i++) {
                x = x + i;
                if (x % k == 12) x = 0;
                else x = x % k;
            }
//            if (x > k) {
//                x = x % k;
//            }
//            if (x == k - 1) {
//                x = 0;
//            }
            sum = sum + x;
            cnt++;
        }
        return sum;
    }

    private static long solve1(int n, int k, int t) {
        long sum = 0;
        int cnt = 0;//东东回答的次数
        int num = 1;
        for (int i = 0; cnt < t; i++) {
            num = num + i;
            if (num > k) {
                num = num % k;
            }
            if (num == k - 1) {
                num = 0;
            }
            if (i % n == 0) {
                sum = sum + num;
                cnt++;
            }
        }
        return sum;
    }
}
