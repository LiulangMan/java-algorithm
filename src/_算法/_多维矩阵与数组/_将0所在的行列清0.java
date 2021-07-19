package _算法._多维矩阵与数组;

import java.util.ArrayList;

public class _将0所在的行列清0 {
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3, 4},
                {5, 0, 7, 8},
                {9, 10, 0, 12},
                {13, 14, 15, 16},
        };
        set_0(matrix);
        mprint(matrix);
    }

    private static void mprint(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void set_0(int[][] matrix) {
        ArrayList<Integer[]> arr = new ArrayList<Integer[]>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    Integer[] s = {i, j};
                    arr.add(s);
                }
            }
        }
        for (int i = 0; i < arr.size(); i++) {
            int x = arr.get(i)[0];
            int y = arr.get(i)[1];
            for (int j = 0; j < matrix.length; j++) {
                matrix[x][j] = 0;
            }
            for (int j = 0; j < matrix.length; j++) {
                matrix[j][y] = 0;
            }
        }
    }
}
