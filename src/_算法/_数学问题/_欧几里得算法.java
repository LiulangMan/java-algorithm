package _算法._数学问题;

public class _欧几里得算法 {

    public static void main(String[] args) {
        System.out.println(gcd(128,64));
    }

    private static int gcd(int m, int n) {
        //即辗转相除法,求最大公约数
        //必背模板
        return n == 0 ? m : gcd(n, m % n);
    }
}