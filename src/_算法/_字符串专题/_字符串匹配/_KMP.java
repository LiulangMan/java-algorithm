package _算法._字符串专题._字符串匹配;


/****
 * next数组的含义
 * next数组的求法
 * **/
public class _KMP {
    public static void main(String[] args) {
        String s = "BABABABCBABABABB";
        String p = "BABABB";
        int ans = indexof1(p, s);
        System.out.println("match:" + ans);
    }


    private static int indexOf(String p, String s) {
        //暴力破解法
        int i = 0;
        int j = 0;
        int sc = i;
        while (sc < s.length()) {
            if (p.charAt(j) == s.charAt(sc)) {
                j++;
                sc++;
                if (j == p.length()) {
                    return i;
                }
            } else {
                i++;
                sc = i;
                j = 0;
            }
        }
        return -1;
    }

    private static int indexof1(String p, String s) {
        //next 数组的匹配算法
        //O（m+n）
        if (p.length() == 0 || s.length() == 0) return -1;
        if (p.length() > s.length()) return -1;
        int sLen = s.length();
        int pLen = p.length();
        int i = 0;
        int j = 0;
        int[] next = next(p);
        while (i < sLen) {
            if (j == -1 || s.charAt(i) == p.charAt(j)) {
                //如果j == -1，表示第一位没有匹配，或者是匹配的（P[i] == S[j]）,都令i++，j++，向后移动
                i++;
                j++;
            } else {
                //i不变，j回溯到next[j]
                j = next[j];
            }
            if (j == pLen) {
                //全部匹配完全，就返回i-Plen
                return i - j;
            }
        }
        return -1;
    }

    private static int[] next(String p) {
        //求next数组
        //递推的求法，如果next[8] == 5,表示 01234 == 34567  之后判断5 是否等于8 ，是则加在前缀后缀中，next(9) = next(8)+1
        //否则，跳到next(5) = 3处，即 012 = 234 ，由上知012 = 234 = 567 ，看8是否又与3相等，如果相等，有0123 = 5678 ，即next（9） = next（5）+1
        //依此递推下去，终点是next(0) = -1;
        int[] next = new int[p.length()];
        char[] parr = p.toCharArray();
        next[0] = -1;
        if (p.length() == 1) {
            return next;
        }
        next[1] = 0;
        int j = 1;
        int k = next[j];//看看位置j的最长匹配前缀在哪
        while (j < p.length() - 1) {
            if (k < 0 || parr[j] == parr[k]) {
                next[++j] = ++k;
            } else {
                k = next[k];
            }
        }
        return next;
    }
}
