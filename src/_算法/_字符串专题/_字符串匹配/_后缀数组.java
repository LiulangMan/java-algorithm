package _算法._字符串专题._字符串匹配;


import java.util.Arrays;

/****
 * 此为大难点：
 *
 * 什么是后缀数组呢?就是串的所有后缀字典排序后，排名和下标的映射。
 *
 * 作用：匹配
 *
 * suffix array （sa）
 *
 * 有rank数组，与sa映射相反。
 *
 * ***/
public class _后缀数组 {
    public static void main(String[] args) {
        String s1 = "ABABABABB";
        String s2 = "BABB";
        Suff[] suffix_array = get_sa2("ABABB");
        System.out.println(match(suffix_array, s2));

    }

    private static boolean match(Suff[] sa, String s2) {
        //返回是否匹配
        //某个字串一定是后缀的前缀
        //二分查找
        int left = 0;
        int right = sa.length - 1;

        int compare = 0;
        while (left <= right) {
            int mide = (left + right) >> 1;
            if (sa[mide].str.length() >= s2.length()) {
                if (sa[mide].str.substring(0, s2.length()).compareTo(s2) == 0) {
                    compare = 0;
                }
            } else {
                compare = sa[mide].str.compareTo(s2);
            }

            if (compare > 0) {
                right = mide - 1;
            } else if (compare < 0) {
                left = mide + 1;
            } else {
                return true;
            }
        }
        return false;
    }

    private static Suff[] get_sa(String s1) {
        //返回后缀数组
        //暴力版本
        Suff[] sa = new Suff[s1.length()];
        for (int i = 0; i < s1.length(); i++) {
            sa[i] = new Suff(s1.substring(i), i);
        }
        Arrays.sort(sa);//依据suff的比较规则排序
        return sa;
    }

    private static Suff[] get_sa2(String s1) {
        //倍增法
        int sLen = s1.length();
        Suff[] sa = new Suff[sLen];
        int[] rank = new int[sLen];
        for (int k = 1; k <= 2 * sLen; k *= 2) {
            for (int i = 0; i < sLen; i++) {
                sa[i] = new Suff(s1.substring(i, Math.min(i + k, sLen)), i);
            }

            if (k == 1) {
                //一个字符 直接排序
                Arrays.sort(sa);
            } else {
                final int kk = k;
                Arrays.sort(sa, (o1, o2) -> {
                    //利用之前的rank
                    int i = o1.index;
                    int j = o2.index;
                    if (rank[i] == rank[j]) {
                        try {
                            return rank[i + kk / 2] - rank[j + kk / 2];
                        } catch (ArrayIndexOutOfBoundsException e) {
                            //前缀相同，长的更大
                            //return o1.str.length() - o2.str.length();
                            //return o1.str.compareTo(o2.str);
                            return -(i - j);
                        }
                    } else {
                        return rank[i] - rank[j];
                    }

                });
            }

            //排序完成后，生成rank数组
            int r = 0;
            rank[sa[0].index] = r;
            for (int i = 1; i < sa.length; i++) {
                if (sa[i].str.compareTo(sa[i - 1].str) == 0) {
                    rank[sa[i].index] = r;
                } else {
                    rank[sa[i].index] = ++r;
                }
            }
        }

        return sa;
    }

}


class Suff implements Comparable<Suff> {
    String str;
    int index;

    Suff(String str, int index) {
        this.str = str;
        this.index = index;
    }

    @Override
    public int compareTo(Suff o) {
        return this.str.compareTo(o.str);
    }

    @Override
    public String toString() {
        return "{Suff:" + "str " + str + " " + "index " + index + "}";
    }
}
