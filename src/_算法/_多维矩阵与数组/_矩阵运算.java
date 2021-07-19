package _算法._多维矩阵与数组;


class Matrix {
    private int r_size;
    private int c_size;
    private long[][] m;

    Matrix(int r_size, int c_size) {
        this.c_size = c_size;
        this.r_size = r_size;
        this.m = new long[r_size][c_size];
    }

    Matrix(Matrix m1) {
        this.r_size = m1.r_size;
        this.c_size = m1.c_size;
        this.m = m1.m;
    }

    Matrix(long[][] arr) {
        this.r_size = arr.length;
        this.c_size = arr[0].length;
        this.m = arr;
    }

    void set_matrix(int r, int c, long value) {
        this.m[r][c] = value;
    }

    void add(Matrix m1) {
        if (m1.c_size == this.c_size && m1.r_size == this.r_size) {
            for (int i = 0; i < r_size; i++) {
                for (int j = 0; j < c_size; j++) {
                    this.m[i][j] += m1.m[i][j];
                }
            }
        }
    }

    void xdot(Matrix m1) {
        //矩阵×乘
        if (this.c_size == m1.r_size) {
            int con = this.c_size;
            long[][] newm = new long[this.r_size][m1.c_size];
            for (int i = 0; i < this.r_size; i++) {
                for (int j = 0; j < m1.c_size; j++) {
                    for (int k = 0; k < con; k++) {
                        newm[i][j] += this.m[i][k] * m1.m[k][j];
                    }
                }
            }
            this.m = newm;
            this.c_size = m1.c_size;
        }
    }

    void ddot(Matrix m1) {
        //矩阵点乘
        if (this.c_size == m1.c_size && this.r_size == m1.r_size) {
            for (int i = 0; i < this.r_size; i++) {
                for (int j = 0; j < this.c_size; j++) {
                    this.m[i][j] *= m1.m[i][j];
                }
            }
        }
    }

    void contrary() {
        //相反数
        for (int i = 0; i < this.r_size; i++) {
            for (int j = 0; j < this.c_size; j++) {
                this.m[i][j] = -this.m[i][j];
            }
        }
    }

    long[][] get_matrix() {
        return this.m;
    }

    void pow(int n) {

        if (this.c_size == this.r_size && n > 0) {
            //方阵
            Matrix e = new Matrix(c_size, c_size);//单位矩阵
            // Matrix k = new Matrix(this);
            e.fill(0);
            for (int i = 0; i < c_size; i++) {
                e.set_matrix(i, i, 1);
            }

            while (n != 0) {
                if ((n & 1) == 1) {
                    e.xdot(this);
                }
                this.xdot(this);
                n >>= 1;
            }
            this.m = e.get_matrix();
        }
    }

    void fill(int x) {
        for (int i = 0; i < r_size; i++) {
            for (int j = 0; j < c_size; j++) {
                this.m[i][j] = x;
            }
        }
    }

    void print_matrix() {
        System.out.println();
        for (int i = 0; i < r_size; i++) {
            System.out.print("{");
            for (int j = 0; j < c_size - 1; j++) {
                System.out.print(this.m[i][j] + ",");
            }
            System.out.print(this.m[i][c_size - 1]);
            System.out.println("}");
        }
    }

    boolean is_square_matrix(){
        return this.c_size == this.r_size;
    }

}

public class _矩阵运算 {
    public static void main(String[] args) {
        long[][] matrix1 = {
                {1, 1, 1},
                {1, 1, 1},
        };
        long[][] matrix2 = {
                {1, 1},
                {1, 1},
                {1, 1}
        };
        long[][] matrix3 = {
                {1, 1, 0},
                {1, 0, 1},
                {1, 0, 0}
        };
        Matrix m1 = new Matrix(matrix1);
        Matrix m2 = new Matrix(matrix2);
        Matrix m3 = new Matrix(matrix3);
        //m1.xdot(m2);
        //m1.print_matrix();
        m3.pow(20);
        m3.print_matrix();
    }
}
