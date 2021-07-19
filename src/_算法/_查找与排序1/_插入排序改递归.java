package _算法._查找与排序1;

/***
 *
 * 排序好1~（n-1）个，把第n个插入数组中
 * ***/
public class _插入排序改递归 {
    public static void main(String[] args) {
        int[] arr = {1, 5, 6, 3, 6, 8, 9, 3, 6, 1, 3};
        int[] sort_arr = sort(arr, arr.length - 1);
        for (int m : sort_arr) {
            System.out.print(m + " ");
        }
    }

    private static int[] sort(int[] arr, int cnt) {
        //升序  cnt：表示还剩几个元素没有排
        if (cnt == 0) {
            return arr;
        }
        for (int i = 0; i < arr.length - cnt; i++) {
            if (arr[i] > arr[arr.length - cnt]) {
                int p = arr[arr.length - cnt];//记录
                for (int j = arr.length - cnt; j >= i; j--) {
                    arr[j] = arr[j - 1];//向前复制
                }
                arr[i] = p;
                return sort(arr, cnt - 1);  //未排-1
            }
        }
        return sort(arr, cnt - 1);

    }
}
