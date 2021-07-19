package _算法._查找与排序2;

import java.util.Arrays;

public class _查找字符串子集 {
    public static void main(String[] args) {
        String s1 = "acd";
        String s2 = "xy3adjc";
        boolean f = find2(s1, s2);
        System.out.println(f);
    }

    private static boolean find2(String s1, String s2) {
        char[] s2_arr = s2.toCharArray();
        Arrays.sort(s2_arr);
        for (int i = 0; i < s1.length(); i++) {
            int index = Arrays.binarySearch(s2_arr, s1.charAt(i));
            if (index < 0) return false;
        }
        return true;
    }

    private static boolean find(String s1, String s2) {
        //暴力
        for (int i = 0; i < s1.length(); i++) {
            char a = s1.charAt(i);
            if (s2.indexOf(a) == -1) return false;
        }
        return true;
    }
}
