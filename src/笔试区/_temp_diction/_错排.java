package 笔试区._temp_diction;

import java.util.ArrayList;
import java.util.Scanner;

public class _错排 {
    private static int ans;
    private static int count;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        ArrayList<Integer> x = new ArrayList<>();
        for (int i = 0; i < T; i++) {
            ans = 0;//初始化
            count = 0;
            int n = sc.nextInt();
            int[] arr = new int[n];
            int[] v = new int[n];//权重
            for (int j = 0; j < n; j++) {
                arr[j] = sc.nextInt();
            }
            for (int j = 0; j < n; j++) {
                v[j] = sc.nextInt();
            }
            int[] brr = new int[n];//brr作为与arr的对比而存在
            System.arraycopy(arr, 0, brr, 0, n);//copy
            find_min_sort(arr, brr, v, 0);
            x.add(ans);
        }
        for (int m : x) {
            System.out.println(m);
        }

    }

    private static void find_min_sort(int[] arr, int[] brr, int[] v, int cnt) {

        if (cnt == arr.length) {
            //出口
            int x = 0;
            for (int i = 0; i < arr.length; i++) {
                x = x + v[i] * abs(arr[i] - brr[i]);
            }
            if (count == 0) ans = x;//第一个排列时

            if (x < ans) ans = x;//更新

            count++;
            return;
        }
        for (int i = cnt; i < arr.length; i++) {
            int temp = arr[cnt];
            arr[cnt] = arr[i];
            arr[i] = temp;

            //剪枝
            boolean c = true;
            for (int j = 0; j <= cnt; j++) {
                if (arr[j] == brr[j]) {//如果有一位与原位相同，非错排
                    c = false;
                    break;
                }
            }
            if (c) {
                find_min_sort(arr, brr, v, cnt + 1);
            }

            //回溯
            temp = arr[cnt];
            arr[cnt] = arr[i];
            arr[i] = temp;
        }
    }

    private static int abs(int n) {
        return n >= 0 ? n : -n;
    }
}
