package _算法._多维矩阵与数组;

/***
 *
 *
 * 1  2  3  4
 * 5  6  7  8
 * 9  10 11 12
 * 13 14 15 16
 *
 *
 *
 * ***/
public class _顺时针打印数组 {
    public static void main(String[] args) {
        int[][] matrix = {
                {1,2,3,4,5},
                {6,7,8,9,10},
                {11,12,13,14,15},
        };

        print(matrix);
    }

    private static void print(int[][] matrix) {
        int c, r;
        int up_limit = 0, left_limit = 0, right_limit = matrix[0].length - 1, down_limit = matrix.length - 1;
        while (left_limit <= right_limit && up_limit <= down_limit) {
            c = left_limit;
            r = up_limit;
            if (right_limit == left_limit) {
                //单列单行情况的补充
                for (int i = up_limit; i <= down_limit; i++) {
                    System.out.print(matrix[i][right_limit] + " ");
                }
                return;
            }

            if (up_limit == down_limit) {
                for (int i = left_limit; i <= right_limit; i++) {
                    System.out.print(matrix[up_limit][i] + " ");
                }
                return;
            }
            while (c <= right_limit) {
                System.out.print(matrix[r][c++] + " ");
            }
            r++;
            c--;
            while (r <= down_limit) {
                System.out.print(matrix[r++][c] + " ");
            }
            r--;
            c--;
            while (c >= left_limit) {
                System.out.print(matrix[r][c--] + " ");
            }
            c++;
            r--;
            while (r > up_limit) {
                System.out.print(matrix[r--][c] + " ");
            }
            left_limit++;
            right_limit--;
            up_limit++;
            down_limit--;
        }
    }
}
