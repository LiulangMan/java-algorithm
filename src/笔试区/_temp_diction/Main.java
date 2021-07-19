package 笔试区._temp_diction;

import java.util.Scanner;

import static java.lang.Math.sqrt;

class Game {

    private int L;
    private int[][] arr;
    private int X;
    private int Y;

    Game(int L, int[][] arr, int X, int Y) {
        this.L = L;
        this.arr = arr;
        this.X = X;
        this.Y = Y;
    }

    int get_l_max() {//主要逻辑
        boolean has = true;//判断范围内还有无补给品
        while (has) {
            for (int i = 0; i < 8; i++) {
                //8个方向
                int add_l = get_add(arr, L, X, Y, i);
                this.L = this.L + add_l;
                //bound(arr, L, X, Y);//边界条件判断
                if (add_l == 0) has = false;//增量为0，已经没有补给品了
            }
        }
        return this.L;
    }

    private int get_add(int[][] arr, int l, int x, int y, int i) {
        int add_l = 0;
        switch (i) {
            //8个方向，顺时针上 右上 右......
            case 0:
                for (int j = 1; j <= l; j++) {
                    if ((x - j >= 0) && arr[x - j][y] != 0) {
                        add_l = add_l + arr[x - j][y];
                        arr[x - j][y] = 0;
                    }
                }
                break;
            case 1:

                for (int j = 1; j <= l; j++) {
                    if ((x - j >= 0 && y + j < arr[0].length) && arr[x - j][y + j] != 0 && (l >= sqrt(2))) {
                        add_l = add_l + arr[x - j][y + j];
                        arr[x - j][y + j] = 0;
                    }
                }
                break;
            case 2:
                for (int j = 1; j <= l; j++) {
                    if ((y + j < arr[0].length) && arr[x][y + j] != 0) {
                        add_l = add_l + arr[x][y + j];
                        arr[x][y + j] = 0;
                    }
                }
                break;
            case 3:
                for (int j = 1; j <= l; j++) {
                    if ((x + j < arr[0].length && y + j < arr[0].length) && arr[x + j][y + j] != 0 && (l >= sqrt(2))) {
                        add_l = add_l + arr[x + j][y + j];
                        arr[x + j][y + j] = 0;
                    }
                }
                break;
            case 4:
                for (int j = 1; j <= l; j++) {
                    if ((x + j < arr[0].length) && arr[x + j][y] != 0) {
                        add_l = add_l + arr[x + j][y];
                        arr[x + j][y] = 0;
                    }
                }
                break;
            case 5:
                for (int j = 1; j <= l; j++) {
                    if ((y - j >= 0 && x + j < arr[0].length) && arr[x + j][y - j] != 0 && (l >= sqrt(2))) {
                        add_l = add_l + arr[x + j][y - j];
                        arr[x + j][y - j] = 0;
                    }
                }
                break;
            case 6:
                for (int j = 1; j <= l; j++) {
                    if ((y - j >= 0) && arr[x][y - j] != 0) {
                        add_l = add_l + arr[x][y - j];
                        arr[x][y - j] = 0;
                    }
                }
                break;
            case 7:
                for (int j = 1; j <= l; j++) {
                    if ((x - j >= 0 && y - j >= 0) && arr[x - j][y - j] != 0 && (l >= sqrt(2))) {
                        add_l = add_l + arr[x - j][y - j];
                        arr[x - j][y - j] = 0;
                    }
                }
                break;
        }
        return add_l;//返回刀增加的长度
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();//组数
        Game[] games = new Game[N];//存储组数的数组
        for (int i = 0; i < N; i++) {
            int M = sc.nextInt();
            int L = sc.nextInt();
            int[][] arr = new int[M][M];
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < M; k++) {
                    arr[j][k] = sc.nextInt();
                }
            }
            int X = sc.nextInt();
            int Y = sc.nextInt();
            games[i] = new Game(L, arr, X, Y);
        }

        for (int i = 0; i < N; i++) {
            System.out.println(games[i].get_l_max());
        }
    }
}
