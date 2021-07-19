package 笔试区._temp_diction2;

import java.util.ArrayList;
import java.util.Scanner;

public class Main5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int n = sc.nextInt();
        ArrayList<Integer> list = new ArrayList<>();
        int left;
        int right;
        for (int i = 0; i < n; i++) {
            left = sc.nextInt();
            right = sc.nextInt();
            String sub = s.substring(left - 1, right);
            list.add(f(sub));

        }

        for (int e : list) {
            System.out.println(e);
        }
    }

    private static int f(String sub) {
        //求回文字串至少有几个
        //滑动窗口 贪婪
        int size = sub.length();
        int lo = 0;
        int hi = lo + size - 1;
        //int cnt = 0;
        int min = Integer.MAX_VALUE;
        while (size > 1) {
            lo = 0;
            hi = lo + size - 1;
            while (hi < sub.length()) {
                if (check(sub, lo, hi)) {
                    //cnt += 1;
                    int cnt1 = 0;
                    int cnt2 = 0;
                    if (hi + 1 < sub.length()) cnt1 += f(sub.substring(hi + 1));
                    if (lo > 0) cnt2 += f(sub.substring(0, lo));
                    min = Math.min(min, cnt1 + cnt2 + 1);
                    return min;
                }
                //cnt = 0;
                lo++;
                hi++;
            }
            size--;
        }
        return Math.min(min, sub.length());
    }

    private static boolean check(String sub, int lo, int hi) {
        while (lo < hi) {
            if (sub.charAt(lo) != sub.charAt(hi)) return false;
            lo++;
            hi--;
        }
        return true;
    }
}
