package _算法._查找与排序2;

/***
 * 输入整数数组
 * 输出整数数组，奇数位于前端，偶数位于后端
 * 思路：快排变体
 *
 * ***/
public class _奇偶分区 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        f(arr);
        for (int m : arr) {
            System.out.print(m + " ");
        }
    }

    private static void f(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            while ((arr[right] % 2 == 0) && (left < right)) {//遇到第一个奇数停下
                right--;
            }
            while ((arr[left] % 2 != 0) && (left < right)) {//遇到第一个偶数
                left++;
            }
            int temp = arr[right];
            arr[right] = arr[left];
            arr[left] = temp;
        }
    }
}
