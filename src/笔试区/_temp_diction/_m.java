package 笔试区._temp_diction;

import java.util.*;

public class _m {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {

            int N = sc.nextInt();
            int M = sc.nextInt();
            int[] arr = new int[N];
            //ArrayList<ArrayList<Integer>> crr = new ArrayList<>();

            int[][] brr = new int[N + 1][N + 1];//辅助数组
            for (int j = 0; j < N; j++) {
                //初始化
                arr[j] = j + 1;
            }
            for (int j = 0; j < N; j++) {
                brr[j][j] = 1;
            }

            for (int j = 0; j < M; j++) {
                String s = sc.next();
                if (s.charAt(0) == '0') {
                    int x1 = Integer.parseInt(s.split(" ")[1]);
                    int x2 = Integer.parseInt(s.split(" ")[2]);
                    if (brr[x1][x1] == 1 && brr[x2][x2] == 1) {
                        brr[x1][x1] = 0;
                        brr[x1][x2] = 1;//加入新集合
                    }
                }
                if (s.charAt(1) == '0') {
                    //独立
                    int count = 0;
                    int c = Integer.parseInt(s.split(" ")[1]);
                    for (int k = 0; k < N; k++) {
                        if (brr[k][c] == 1) {
                            brr[k][c] = 0;
                            break;
                        }
                    }

                    for (int k = 0; k < N; k++) {
                        for (int l = 0; l < N; l++) {
                            if (brr[k][l] == 1){
                                count ++;
                                break;
                            }
                        }
                        if (count == 0){
                            brr[k][c]= 1;
                        }
                    }

                }
                if (s.charAt(2) == '0') {
                    //输出
                    int count = 0;
                    int r = 0;
                    int c = Integer.parseInt(s.split(" ")[1]);
                    for (int k = 0; k < N; k++) {
                        if (brr[k][c] == 1) {
                            r = k;
                            break;
                        }
                    }
                    for (int k = 0; k < N; k++) {
                        if (brr[r][k] == 1) count++;
                    }
                    System.out.println(count);

                }
            }

        }
    }
}
