package 笔试区._temp_diction;
//
//　　兰顿蚂蚁，是于1986年，由克里斯·兰顿提出来的，属于细胞自动机的一种。
//
//        　　平面上的正方形格子被填上黑色或白色。在其中一格正方形内有一只“蚂蚁”。
//        　　蚂蚁的头部朝向为：上下左右其中一方。
//
//        　　蚂蚁的移动规则十分简单：
//        　　若蚂蚁在黑格，右转90度，将该格改为白格，并向前移一格；
//        　　若蚂蚁在白格，左转90度，将该格改为黑格，并向前移一格。
//
//        　　规则虽然简单，蚂蚁的行为却十分复杂。刚刚开始时留下的路线都会有接近对称，像是会重复，但不论起始状态如何，蚂蚁经过漫长的混乱活动后，会开辟出一条规则的“高速公路”。
//
//        　　蚂蚁的路线是很难事先预测的。
//
//        　　你的任务是根据初始状态，用计算机模拟兰顿蚂蚁在第n步行走后所处的位置。
//        输入格式
//        　　输入数据的第一行是 m n 两个整数（3 < m, n < 100），表示正方形格子的行数和列数。
//        　　接下来是 m 行数据。
//        　　每行数据为 n 个被空格分开的数字。0 表示白格，1 表示黑格。
//
//        　　接下来是一行数据：x y s k, 其中x y为整数，表示蚂蚁所在行号和列号（行号从上到下增长，列号从左到右增长，都是从0开始编号）。s 是一个大写字母，表示蚂蚁头的朝向，我们约定：上下左右分别用：UDLR表示。k 表示蚂蚁走的步数。
//        输出格式
//        　　输出数据为两个空格分开的整数 p q, 分别表示蚂蚁在k步后，所处格子的行号和列号。

import java.util.Scanner;

public class _兰顿蚂蚁 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int[][] arr = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
        int x = sc.nextInt();
        int y = sc.nextInt();
        char s = sc.next().charAt(0);
        int k = sc.nextInt();
        int[] ans = find(arr, x, y, s, k);
        System.out.print(ans[0] + " " + ans[1]);


    }

    private static int[] find(int[][] arr, int x, int y, char s, int k) {
        int diction = finddiction(s);
        int color;
        for (int i = 1; i <= k; i++) {
            color = arr[x][y];//记录颜色
            arr[x][y] = 1 - arr[x][y];//颜色变换
            if (color == 0) {//白色
                diction = diction >= 1 ? diction - 1 : 3;//左转
            }

            else {
                diction = (diction + 1) % 4;
            }
            if (diction == 0) x--;
            if (diction == 1) y++;
            if (diction == 2) x++;
            if (diction == 3) y--;

            //System.out.println(x+" "+y);
        }
        int[] ans = {x, y};
        return ans;

    }

    private static int finddiction(char s) {
        if (s == 'U') return 0;
        if (s == 'R') return 1;
        if (s == 'D') return 2;
        if (s == 'L') return 3;
        return -1;
    }
}
