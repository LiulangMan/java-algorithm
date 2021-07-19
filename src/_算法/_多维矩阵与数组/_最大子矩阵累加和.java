package _算法._多维矩阵与数组;

import java.util.Arrays;


public class _最大子矩阵累加和 {
    public static void main(String[] args) {
        int[][] matrix = {
                {-1, -1, -1},
                {-1, 2, -1},
                {-1, 2, -1}
        };
        System.out.println(findzmax(matrix));
    }

    private static int findzmax(int[][] matrix) {
        int M = matrix.length;
        int N = matrix[0].length;
        int max = 0;//记录最大值
        int[] sum = new int[N];
        int beginrow = 0;//开始行
        while (beginrow < M) {
            for (int i = beginrow; i < M; i++) {
                //从开始到结束行
                for (int j = 0; j < N; j++) {
                    sum[j] += matrix[i][j];
                }
                int t = _最大子数组累加和.findmax(sum);
                if (t > max) {
                    max = t;
                }
            }
            Arrays.fill(sum, 0);
            beginrow++;
        }
        return max;
    }
}
