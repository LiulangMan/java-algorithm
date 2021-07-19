package _算法._位算法;

import java.util.Scanner;

public class _将整数的奇偶位交换 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int x1 = 0xaaaaaaaa;   //1010......
        int x2 = 0x55555555;   //0101......
        int n1 = n&x1;//取出偶数位
        int n2 = n&x2;//去除奇数位
        int ans = (n1>>1)^(n2<<1);//移位后异或
        System.out.println(ans);
    }
}
