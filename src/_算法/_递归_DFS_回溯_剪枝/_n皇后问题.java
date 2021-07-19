package _算法._递归_DFS_回溯_剪枝;

import java.util.Scanner;

/***
 *
 * 著名问题：
 * 在一个 n*n 的棋盘上 放置n个棋子，
 * 使得每行每列每个对角线上只有一个棋子，
 * 求其摆放的方法数
 *
 * 解法：DFS + 回溯 + 剪枝
 *
 * ***/

public class _n皇后问题 {

    private static int count;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        nQueer(n);
        System.out.println(count);

    }

    private static void nQueer(int n) {
        int[] arr = new int[n + 1];

        dfs(arr, 1);//从第1行开始检索

    }

    private static void dfs(int[] arr, int x) {
        if (x == arr.length) {
            count++;
            return;
        }

        for (int i = 1; i < arr.length; i++) {
            if (check(arr, x, i)) {
                arr[x] = i; // 标记 x行的皇后放在i列
                dfs(arr, x + 1);//下一状态
                arr[x] = 0; //回溯
            }
        }
    }

    private static boolean check(int[] arr, int x, int y) {
        for (int i = 1; i < x; i++) {
            if (arr[i] == y) return false;
        }

        for (int i = -arr.length; i < arr.length; i++) {
            if (x + i >= 1 && x + i <= arr.length - 1) {
                if (y + i >= 1 && y + i <= arr.length - 1 && arr[x + i] == y + i) return false;
                if (y - i >= 1 && y - i <= arr.length - 1 && arr[x + i] == y - i) return false;
            }
        }
        return true;
    }
}
