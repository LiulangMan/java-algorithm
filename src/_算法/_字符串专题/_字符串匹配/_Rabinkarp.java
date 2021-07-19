package _算法._字符串专题._字符串匹配;


public class _Rabinkarp {
    final static long seed = 31;

    public static void main(String[] args) {
        String s = "ABABABA";
        String p = "ABA";
        match1(p, s);
    }

    /**
     * p:模式
     * s:源串
     */
    private static void match(String p, String s) {
        //复杂度 o（m*n）
        long hash_p = hash(p);
        long hash_s;
        int len_p = p.length();
        for (int i = 0; i + len_p <= s.length(); i++) {
            hash_s = hash(s.substring(i, i + len_p));//截取字符串
            if (hash_p == hash_s) {
                System.out.println("match:" + i);
            }
        }
    }

    private static void match1(String p, String s) {
        long hash_p = hash(p);
        long[] hash_s = hash(s, p.length());

        for (int i = 0; i < hash_s.length; i++) {
            if (hash_p == hash_s[i]) {
                if (p.equals(s.substring(i, i + p.length())))
                    System.out.println("match:" + i);
            }
        }
    }

    private static long hash(String src) {
        long hash = 0;
        for (int i = 0; i < src.length(); i++) {
            hash = seed * hash + src.charAt(i);
        }
        return hash % Long.MAX_VALUE;
    }


    //滚动hash
    private static long[] hash(final String src, final int n) {
        //复杂度m+n
        long[] res = new long[src.length() - n + 1];
        res[0] = hash(src.substring(0, n));
        for (int i = n; i < src.length(); i++) {
            char newChar = src.charAt(i);
            char oldChar = src.charAt(i - n);
            long v = (res[i - n] * seed + newChar - oldChar * ex(seed, n)) % Long.MAX_VALUE;
            res[i - n + 1] = v;
        }
        return res;
    }

    private static long ex(long num, int n) {
        long ans = 1;
        long k = num;
        while (n != 0) {
            if ((n & 1) == 1) {
                ans *= k;
            }
            k = k * k;
            n >>>= 1;
        }
        return ans;
    }
}
