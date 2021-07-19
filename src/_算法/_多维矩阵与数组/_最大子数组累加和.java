package _算法._多维矩阵与数组;

public class _最大子数组累加和 {
    public static void main(String[] args) {
        int[] arr = {-3,3,-3};
        int max = findmax(arr);
        System.out.println(max);
    }

    public static int findmax(int[] arr) {
        //扔弃负数的策略
        //暴力破解就不写了，O(n^2)
        int left = 0;
        int right = 0;
        int sum = 0;
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            sum = sum + arr[i];
            if (sum > 0) {
                if (sum > max) {
                    max = sum;
                    right = i;
                }
            } else {
                sum = 0;
                left = i + 1;
            }
        }
//        System.out.print("最大子数组：");
//        for (int i = left; i <= right ; i++) {
//            System.out.print(arr[i]+" ");
//        }
//        System.out.println();
        return max;
    }
}
