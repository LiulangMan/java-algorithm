package _算法._递归_DFS_回溯_剪枝;


import java.util.Scanner;

/***
 * 在一个二维数组中，有water，其中八连通认为是同一个水洼，
 *
 * XXX
 * XWX
 * XXX
 *
 * 输入一个二维数组，输出水洼数
 *
 *
 *
 * 如：
 *
...w.....w.
....w....w.
...w......w
...........
...........
..w........
.w.w.......
w.w.w......
.w.w.......
..w........

 * 输出：3
 *
 *
 * ***/
public class _水洼数目 {
    private static int count;

    public static void main(String[] args) {
        //消除法:必背模板

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();//行
        int m = sc.nextInt();//列
        char[][] arr = new char[n][];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.next().toCharArray();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 'w') {
                    findway(arr, i, j);//清除一个水洼
                    count ++ ;//计数
                }
            }
        }

        System.out.println(count);
    }

    private static void findway(char[][] arr, int i, int j) {
        //每遍历一个点，就把'w'变成'.'
        arr[i][j] = '.';

        for (int k = -1; k < 2; k++) {// -1 0 1
            for (int l = -1; l < 2; l++) {// -1 0 1
                if (k == 0 && l == 0)continue;

                if (i+k>=0 && i+k<= arr.length-1 && j+l>=0 && j+l<=arr[0].length-1){
                    if (arr[i+k][j+l] == 'w'){
                        findway(arr,i+k,j+l);
                    }
                }
            }
        }
    }
}
