package _算法._多维矩阵与数组;

//{1, 2,  3,  4 },
//{5, 6,  7,  8 },
//{9, 10, 11, 12}};
//打印：1 2 5 9 6 3 4 7 10 11 8 12

public class _Z形打印数组 {
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}};
        zprint(matrix);
    }

    private static void zprint(int[][] matrix) {
        int up_limit = 0, down_limit = matrix.length - 1, left_limit = 0, right_limit = matrix[0].length - 1;
        int r = 0, c = 0; // 行 列
        int num = matrix.length * matrix[0].length;
        int i = 0;
        boolean rightup = true;
        while (i < num) {
            System.out.print(matrix[r][c] + " ");
            i++;
            if (rightup) {
                if (r - 1 < up_limit || c + 1 > right_limit) {
                    rightup = false;
                    if (r - 1 < up_limit && c + 1 <= right_limit) {
                        c++;
                    } else {
                        r++;
                    }
                    continue;
                }
                r -= 1;
                c += 1;
            } else {
                if (r + 1 > down_limit || c - 1 < left_limit) {
                    rightup = true;
                    if (r + 1 <= down_limit && c - 1 < left_limit) {
                        r++;
                    } else {
                        c++;
                    }
                    continue;
                }
                r += 1;
                c -= 1;
            }
            // i++;
        }
    }
}
