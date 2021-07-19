package _算法._数学问题;

import java.util.Scanner;

/***
 * 有多堆石头，两个人轮流从其中一堆取出任意多的石头（至少为1），最后取完的为赢家
 * 现在Nim第一个取，假设双方都采取最有策略，则输出Nim的胜负结果
 *
 * 输入：第一行输入n 表示石头堆数
 * 第二行 输入n个数，每个数表示每堆石头的个数
 * 输入：输出1 代表Nim赢，反之输出0
 *
 *
 * 游戏的解法结论：
 * n堆石头的异或结果如果为0，下一次操作将其变为不为0，-------->0
 * 如果一开始为0，一旦操作，就不为0，那么下一方总可以变为0，以此类推，最后为0的石头堆已经是输家
 * ***/
public class _Nim游戏 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = sc.nextInt();
        }

        for (int i = 1; i < arr.length; i++) {
            arr[0] ^= arr[i];
        }
        System.out.println(arr[0] != 0 ? 1 : 0);

    }
}
