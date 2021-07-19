package _算法._查找与排序1;


import java.util.Arrays;

/*递归算法：

 * 找重复：
 * 找变化：变化的量作为参数
 * 找边界：出口
 *

    反转字符串
 */
public class _递归基础 {
    //private static int count = 0;
    private static int count1 = 0;

    public static void main(String[] args) {
        //反转字符
        String a = "abcde";
        String s = reverse(a, 4);
        System.out.println(s);

        //斐波拉契线性递归
        int n = 10;
        int[] remember = new int[n + 1];//辅助空间
        remember[1] = 1;
        remember[2] = 1;
        Arrays.fill(remember, 0);
        //int ans = fib(n, remember);
        for (int i = 1; i <= 10; i++) {
            System.out.print(fib(i, remember)+" ");
        }
        //System.out.println(ans);
    }

    private static int fib(int n, int[] remember) {

        if (n == 1 || n == 2) return 1;
        if (remember[n] != 0) {
            return remember[n];//被记录
        }
        //如果没被记录，则记录
        remember[n] = fib(n - 1, remember) + fib(n - 2, remember);
        return remember[n];
    }

    private static String reverse(String a, int end) {
        if (end == 0) return "" + a.charAt(0);
        return a.charAt(end) + reverse(a, end - 1);
    }
}
