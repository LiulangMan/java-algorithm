package _算法._字符串专题;

import java.util.Arrays;

public class _判断是否变形词 {
    public static void main(String[] args) {
        String s1 = "abcdefg";
        String s2 = "agcdegf";
        System.out.println(sam_string_c(s1, s2));
    }

    private static boolean sam_string(String s1, String s2) {
        //JDK解法
        if (s1.length() != s2.length()) return false;
        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        return Arrays.equals(arr1, arr2);
    }

    private static boolean sam_string_c(String s1, String s2) {
        //计数解法
        if (s1.length() != s2.length()) return false;
        int[] cnt = new int[128];
        for (int i = 0; i < s1.length(); i++) {
            int index = s1.charAt(i);
            cnt[index]++;
        }
        for (int i = 0; i < s2.length(); i++) {
            int index = s2.charAt(i);
            cnt[index]--;
            if (cnt[index] < 0) return false;
        }
        return true;

    }
}
