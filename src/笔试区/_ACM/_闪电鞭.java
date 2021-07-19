package 笔试区._ACM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @program: suanfa_by_java
 * @author: 一树
 * @data: 2020/11/14 10:03
 */
public class _闪电鞭 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int[] reverse = new int[n];
        long[] dp1 = new long[n];
        long[] dp2 = new long[n];
        for (int i = 0; i < n; i++) {
            reverse[i] = arr[n - i - 1];
            dp1[i] = arr[i] + (i - 1 >= 0 ? dp1[i - 1] : 0);
            dp2[i] = reverse[i] + (i - 1 >= 0 ? dp2[i - 1] : 0);
        }


        List<Long> list = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int cnt = sc.nextInt();
            int left = sc.nextInt();
            int right = sc.nextInt();
            if ((cnt & 1) == 0) {
                //偶数次
                list.add(dp1[right - 1] - dp1[left - 1] + arr[left - 1]);
            } else {
                //奇数次
                list.add(dp2[right - 1] - dp2[left - 1] + reverse[left - 1]);
            }
        }

        for (Long aLong : list) {
            System.out.println(aLong);
        }
    }
}
