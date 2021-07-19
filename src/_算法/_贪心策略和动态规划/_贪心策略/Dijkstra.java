package _算法._贪心策略和动态规划._贪心策略;

import java.util.Arrays;
import java.util.Stack;

public class Dijkstra {
    private static final int Nan = Integer.MAX_VALUE / 2 - 1;
    private static int[] path;//路由矩阵

    public static void main(String[] args) {
        int[][] matrix = {
                {0, 5, 8, Nan, Nan,},
                {5, 0, 1, Nan, Nan},
                {8, 1, 0, 5, 3},
                {Nan, Nan, 5, 0, 2},
                {Nan, Nan, 3, 2, 0}};
        path = new int[matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            int[] dis = dijkstra(matrix, i);
            for (int j = 0; j < matrix.length; j++) {
                if (i != j) {
                    System.out.print(i + "------>" + j + "   dis:   " + dis[j] + "      path:      ");

                    System.out.print(i);
                    //中转数组逆序
                    int p = j;
                    Stack<Integer> stack = new Stack<>();
                    while (path[p] != -1) {
                        stack.push(path[p]);
                        p = path[p];
                    }
                    while (!stack.empty()) {
                        System.out.print("->" + stack.pop());
                    }
                    System.out.println("->" + j);
                }
            }
        }
    }


    private static int[] dijkstra(int[][] matrix, int from) {
        int Len = matrix[from].length;
        boolean[] visit = new boolean[Len];
        Arrays.fill(visit, false);
        Arrays.fill(path, -1);
        visit[from] = true;


        for (int i = 0; i < Len; i++) {
            int min = Nan;
            int rec = Nan;//标记
            //找min
            for (int j = 0; j < Len; j++) {
                if (!visit[j] && min > matrix[from][j]) {
                    min = matrix[from][j];
                    rec = j;
                }
            }
            //记录
            if (rec != Nan) visit[rec] = true;

            //更新
            for (int j = 0; j < Len; j++) {
                if (!visit[j] && matrix[from][rec] + matrix[rec][j] < matrix[from][j] && rec != Nan) {
                    matrix[from][j] = matrix[from][rec] + matrix[rec][j];
                    path[rec] = j;
                }
            }


        }

        return matrix[from];
    }
}
