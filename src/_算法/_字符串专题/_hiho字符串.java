package _算法._字符串专题;


import java.util.Scanner;

//给定一段字符，返回恰好包含hhio的字符串最短，没有则返回-1
//最短摘要算法题
public class _hiho字符串 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int ans = find(s);
        System.out.println(ans);
    }

    private static int find(String s) {
        int i;
        int j = -1;
        int p_j = -1;
        int min = Integer.MAX_VALUE;
        for (i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'h' || s.charAt(i) == 'i' || s.charAt(i) == 'o') {
                if (p_j == -1) {
                    j = i + 1;
                }
                while (j < s.length() && !check(s, i, j)) {
                    j++;
                }
                if (j == s.length()) j--;
                if (check2(s, i, j) && (j - i + 1 < min)) {
                    min = j - i + 1;
                }
                p_j = j;
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    private static boolean check(String w, int i, int j) {
        int c1 = 0, c2 = 0, c3 = 0;
        for (int k = i; k <= j; k++) {
            if (w.charAt(k) == 'h') c1++;
            if (w.charAt(k) == 'i') c2++;
            if (w.charAt(k) == 'o') c3++;
        }
        return c1 >= 2 && c2 >= 1 && c3 >= 1;

    }


    private static boolean check2(String w, int i, int j) {
        int c1 = 0, c2 = 0, c3 = 0;
        for (int k = i; k <= j; k++) {
            if (w.charAt(k) == 'h') c1++;
            if (w.charAt(k) == 'i') c2++;
            if (w.charAt(k) == 'o') c3++;
        }
        return c1 == 2 && c2 == 1 && c3 == 1;
    }
}
