package _算法._字符串专题;

import java.util.Arrays;

public class _判断有无重复字符 {
    public static void main(String[] args) {
        String str = "abcdef";
        System.out.println(is_uniqu_char(str));
    }

    private static boolean is_uniqu_char(String str) {
        //计数思维
        if (str.isEmpty()) {
            return true;
        }
        int[] cnt = new int[128];
        Arrays.fill(cnt, 0);
        for (int i = 0; i < str.length(); i++) {
            int index = str.charAt(i);
            cnt[index]++;
            if (cnt[index]>1){
                return false;
            }
        }
//
//        for (int i = 0; i < cnt.length; i++) {
//            if (cnt[i]>1){
//                return false;
//            }
//        }
        return true;
    }
}
