package _算法._贪心策略和动态规划._贪心策略;


import java.util.Scanner;

/***
 * 给一个串S，空串T
 * 现有两种操作：1，将S的首字符删除，加入T；2，将S的尾字符删除，加入T
 * 求一种策略，使得T的字典序尽可能小
 *
 * 输入：S
 * 输出：T
 *
 * ***/
public class _字典序最小 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String S = sc.next();
        sc.close();

        String T = findway(S);
        System.out.println(T);
    }

    private static String findway(String s) {
        StringBuilder sb = new StringBuilder();//空串
        int start = 0;
        int end = s.length() - 1;

        while (start < end) {
            //如果字符不相同，直接选择
            if (s.charAt(start) != s.charAt(end)) {
                if (s.charAt(start) < s.charAt(end)) {
                    sb.append(s.charAt(start));
                    start++;
                } else {
                    sb.append(s.charAt(end));
                    end--;
                }
            }
            //如果字符相同，查看下一不同字符再选择
            else {
                int left = start;
                int right = end;
                left++;
                right--;
                while (left < right && s.charAt(left) == s.charAt(right)) {
                    left++;
                    right--;
                }

                if (s.charAt(left)<s.charAt(right)){
                    sb.append(s.charAt(start));
                    start++;
                }
                else {
                    sb.append(s.charAt(end));
                    end--;
                }
            }
        }
        //把最后一个字符加入
        sb.append(s.charAt(start));

        return sb.toString();
    }
}
