package _算法._多维矩阵与数组;

public class _找出方阵中最大正方形 {
    public static void main(String[] args) {
        int[][] matrix = {
                {0, 1, 1, 1, 1},
                {0, 1, 0, 0, 1},
                {0, 1, 0, 0, 1},
                {0, 1, 1, 1, 1},
                {0, 1, 0, 1, 1}
        };
        int ans = findmax(matrix);
        System.out.println(ans);
    }

    private static int findmax(int[][] matrix) {
        int N = Math.min(matrix.length, matrix[0].length);//取最小
        int r, c;
        while (N > 0) {
            for (r = 0; r <= matrix.length - N; r++) {
                for (c = 0; c <= matrix[0].length - N; c++) {
                    if (matrix[r][c] == 1) {
                        //如果是1
                        if (r + N <= matrix.length && c + N <= matrix[0].length) {
                            //如果存在对角
                            //遍历
                            int i = r, j = c;
                            boolean find = true;
                            while (i < r + N) {//down
                                if (matrix[i][j] == 0) {
                                    find = false;
                                    break;
                                }
                                i++;
                            }
                            while (j < c + N && find) {//right
                                i = r + N - 1;
                                if (matrix[i][j] == 0) {
                                    find = false;
                                    break;
                                }
                                j++;
                            }

                            while (i >= r && find) {//up
                                j = c + N - 1;
                                if (matrix[i][j] == 0) {
                                    find = false;
                                    break;
                                }
                                i--;
                            }

                            while (j >= c && find) {//left
                                i = r;
                                if (matrix[i][j] == 0) {
                                    find = false;
                                    break;
                                }
                                j--;
                            }
                            if (find) return N;
                        }
                    }
                }
            }
            N--;
        }
        return 0;
    }
}
