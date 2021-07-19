package _算法._递归_DFS_回溯_剪枝;

import java.util.Scanner;

/***

 DFS:深度优先
 BFS:广度优先




 0 0 5 3 0 0 0 0 0
 8 0 0 0 0 0 0 2 0
 0 7 0 0 1 0 5 0 0
 4 0 0 0 0 5 3 0 0
 0 1 0 0 7 0 0 0 6
 0 0 3 2 0 0 0 8 0
 0 6 0 5 0 0 0 0 9
 0 0 4 0 0 0 0 3 0
 0 0 0 0 0 9 7 0 0


 3 7 0 0 0 0 0 6 0
 9 0 4 0 2 6 0 0 1
 2 0 1 0 0 3 0 0 0
 0 0 0 6 0 4 9 7 0
 7 0 9 0 3 0 0 0 0
 0 0 0 0 0 8 0 2 0
 0 1 3 9 0 0 0 0 0
 0 0 0 0 8 0 0 0 0
 0 0 0 0 0 7 2 8 0

 0 0 5 6 0 0 4 0 0
 0 0 0 4 7 0 2 9 0
 0 0 4 0 0 0 0 0 7
 5 3 0 0 0 0 0 0 0
 0 6 0 0 0 0 9 8 0
 0 7 0 0 8 0 0 3 4
 0 0 0 0 0 0 0 0 0
 8 4 0 0 3 7 5 0 0
 0 0 0 0 0 0 7 4 9


 ***/
public class _数独_DFS {

    public static void main(String[] args) {
        int[][] table = new int[9][9];//数独桌面
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int m = sc.nextInt();
                table[i][j] = m;//初始化 0 代表待填的格子
            }
        }

        dfs(table, 0, 0);

    }

    private static void dfs(int[][] table, int x, int y) {
        if (x == 9) {
            //终点
            System.out.println();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    System.out.print(table[i][j] + " ");
                }
                System.out.println();
            }
            System.exit(0);//程序结束
        }

        if (table[x][y] == 0) {
            for (int i = 1; i <= 9; i++) {
                if (check(table, x, y, i)) {

                    table[x][y] = i;//下一状态

                    dfs(table, x + (y + 1) / 9, (y + 1) % 9);

                }
            }

            table[x][y] = 0;//回溯：返回当前状态，交给下一个平行状态

            //如果没有数字可以填写，则此路径无解
        } else {

            //如果table[x][y] != 0,找下一个
            dfs(table, x + (y + 1) / 9, (y + 1) % 9);
        }
    }

    private static boolean check(int[][] table, int x, int y, int i) {
        //先检查行：
        for (int j = 0; j < 9; j++) {
            if (j != y && table[x][j] == i) return false;
        }

        //再检查列
        for (int j = 0; j < 9; j++) {
            if (j != x && table[j][y] == i) return false;
        }

        //最后检查方格

        int m = x / 3;//x:0 1 2 -> m:0   x:3 4 5->m:1   x:6 7 8->m:2
        int n = y / 3;//y:0 1 2 -> n:0   y:3 4 5->n:1   y:6 7 8->n:2

        for (int j = m * 3; j < (m + 1) * 3; j++) {
            for (int k = n * 3; k < (n + 1) * 3; k++) {
                if (j != x && k != y) {
                    if (table[j][k] == i) return false;
                }
            }
        }
        return true;
    }
}
