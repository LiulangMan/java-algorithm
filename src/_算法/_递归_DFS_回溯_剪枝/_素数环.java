package _算法._递归_DFS_回溯_剪枝;


import java.util.Scanner;

/***
 * 输入一个数n（n<=16）,代表1~n的数，
 * 进行排序，使得相连两个数的和为素数（包括第一个和最后一个的和）
 * 输出排序结果（从1开始，同一个环输出一次）
 *
 * ***/
public class _素数环 {
    private static int[] arr;
    private static int[] rem;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        arr = new int[n];//辅助数组
        rem = new int[n+1];//空间换时间

        arr[0] = 1;//从1开始
        rem[1] = 1;//
        dfs(1);
    }

    private static void dfs(int cur) {
        if (cur == arr.length) {
            for (int e:arr) {
                System.out.print(e+" ");
            }
            System.out.println();
            return;
        }
        for (int i = 1; i <= arr.length; i++) {
            if (check(i, cur))//i没有在arr出现过，以及ans[cur-1]+i是素数
            {
                arr[cur] = i;
                rem[i] = 1;
                dfs(cur + 1);
                arr[cur] = 0;
                rem[i] = 0;
            }
        }
    }

    private static boolean check(int i, int cur) {
        //唯一性判断
//        for (int e : arr) {
//            if (e == i) return false;
//        }
        if (rem[i] == 1)return false;

        //素数判断:必背
        int p = arr[cur - 1] + i;
        for (int j = 2; j * j <= p; j++) {
            if (p % j == 0) return false;
        }

        //首项末项判断
        if (cur == arr.length - 1){
            p = arr[0] + i;
            for (int j = 2; j * j <= p; j++) {
                if (p % j == 0) return false;
            }
        }

        return true;
    }
}
