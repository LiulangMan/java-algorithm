package 笔试区._temp;


import java.io.IOException;
import java.util.*;

//求最长不重复子串---滑动窗口算法

public class Main3 {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        String s = sc.next();

        System.out.println(f(s));

    }

    private static String f(String s) {
        //滑动窗口思路

        if (s.length() == 0) return null;


        int lo = 0;
        int hi = lo + 1;
        int max = 0;
        int start = 0;

        LinkedList<Character> buff = new LinkedList<>();
        buff.add(s.charAt(lo));//初始化
        while (hi < s.length()) {
            if (buff.indexOf(s.charAt(hi)) == -1) {
                buff.add(s.charAt(hi++));

            } else {
                //更新

                if (max < hi - lo) {
                    max = hi - lo;
                    start = lo;
                }

                //慢开始算
                lo++;
                hi = lo + 1;
                buff.clear();
                if (lo < s.length()) buff.add(s.charAt(lo));
            }
        }

        return s.substring(start, start + max);
    }
}