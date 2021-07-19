package _算法._查找与排序2;


//必须记住的常用算法！！！！！！

public class _全排列 {
    public static int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

    public static void main(String[] args) {
        f(0);
    }

    private static void f(int k) {
//       出口
        if (k == 10) {
            for (int m : arr) {
                System.out.print(m + " ");
            }
            System.out.println();
            return;
        }

        for (int i = k; i < 10; i++) {
//          枚举
            int t = arr[i];
            arr[i] = arr[k];
            arr[k] = t;

//          移交下一层
            f(k + 1);

//          回溯
            t = arr[i];
            arr[i] = arr[k];
            arr[k] = t;
        }
    }
}
