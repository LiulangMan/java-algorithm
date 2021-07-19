package _算法._查找与排序2;
/***
 *
 * 分治的完美诠释：归并排序
 *
 * 简分重合
 *
 * 分：数组中间
 * 合：两个有序数组的合并
 *
 * ***/
public class _归并排序 {
    public static void main(String[] args) {
        int[] arr = {1, 4, 2, 5, 4, 6, 7, 8, 3, 2, 1, 3, 5, 6, 2, 10};
        int[] brr = new int[arr.length];//辅助空间

        gSort(arr, brr, 0, arr.length - 1);
        for (int m : arr) {
            System.out.print(m + " ");
        }

    }

    private static void gSort(int[] arr, int[] brr, int start, int end) {
        if (start == end) {//出口
            return;
        }
        int mid = start + ((end - start) >> 1);//中点
        gSort(arr, brr, start, mid);
        gSort(arr, brr, mid + 1, end);
        //合并
        int left = start;//左指针
        int right = mid + 1;//右指针
        int k = 0;
        while (left <= mid && right <= end) {
            if (arr[left] <= arr[right]) {
                brr[k++] = arr[left++];
            } else {
                brr[k++] = arr[right++];
            }
        }
        while (left <= mid && right > end) {
            brr[k++] = arr[left++];
        }
        while (right <= end && left > mid) {
            brr[k++] = arr[right++];
        }
        System.arraycopy(brr, 0, arr, start, end - start + 1);
    }

}
