package _算法._位算法;

import java.util.Random;

/*
 * 找数组中唯一重复出现的值
 *
 * 算法：异或运算
 * A^A = 0; B^0=B
 *
 * 思路：把非重复arr中的数全部异或一遍 那么就消除了重复的值；再把结果与arr再异或一遍，唯一的重复值就被找到了
 */
public class _找出数组中重复的值 {
    public static void main(String[] args) {
        int N = 1000;
        int[] arr = new int[N + 1];
        for (int i = 0; i < N; i++) {
            arr[i] = i + 1;
        }
        int index = new Random().nextInt(N) + 1;
        int vaule = new Random().nextInt(N) + 1;
        arr[arr.length - 1] = vaule;

        //二进制交换
        arr[N] = arr[N] ^ arr[index];
        arr[index] = arr[N] ^ arr[index];
        arr[N] = arr[N] ^ arr[index];

        int x = 0;
        //计算非重复的
        for (int i = 1; i <= N; i++) {
            x = x ^ i;
        }
        //找出答案
        for (int i = 0; i <= N; i++) {
            x = x ^ arr[i];
        }
        for (int m : arr) {
            System.out.print(m + " ");
        }
        System.out.println();
        System.out.println(x);

    }
}
