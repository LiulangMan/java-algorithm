package _算法._查找与排序1;


/*
 * 一般用辗转相除法：
 * m>n;
 * 如果m%n==0，则n为最大公约数
 * 否则m%n==p;
 * m = n, n = p
 * 继续上述过程
 * */

public class _最大公约数 {
    public static void main(String[] args) {
        int m = 50;
        int n = 20;

        System.out.println(f(m, n));
    }

    private static int f(int m, int n) {
        if (m < n) {//交换
            m = m ^ n;
            n = m ^ n;
            m = m ^ n;
        }
        int p = m % n;
        if (p == 0) return n;
        return f(n, p);


//        //循环法
//        int p = 0;
//        while (m % n != 0) {
//            p = m % n;
//            m = n;
//            n = p;
//        }
//        return n;
    }
}
