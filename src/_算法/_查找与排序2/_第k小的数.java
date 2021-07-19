package _算法._查找与排序2;


public class _第k小的数 {
    public static void main(String[] args) {
        int[] arr = {1, 4, 6, 2, 3, 5, 7, 5, 3, 6, 7, 8, 9, 5};
        int k = 3;
        int ans = SelectK(arr, 0, arr.length - 1, k);
        for (int i = 1; i <= arr.length; i++) {
            System.out.print(SelectK(arr, 0, arr.length - 1, i) + " ");
        }
        System.out.println();
        for (int i = 1; i <= arr.length; i++) {
            System.out.print(SelectK2(arr, 0, arr.length - 1, i) + " ");
        }

    }

    public static int SelectK(int[] arr, int start, int end, int k) {
        if (k > arr.length) return -1;
        if (start == end) return arr[end];
        int pivot = arr[start];
        int left = start + 1;
        int right = end;
        while (left < right) {
            //右指针左移动，找第一个比pivot小的值
            while ((left < right) && (arr[right] > pivot)) {
                right--;
            }
            //左指针右移动，找第一个比pivot大的值
            while ((left < right) && (arr[left] <= pivot)) {
                left++;
            }
            if ((arr[right] != arr[left]) && (left < right)) {
                int temp = arr[right];
                arr[right] = arr[left];
                arr[left] = temp;
            }
        }
        int p;//哨兵下标
        if (arr[left] <= pivot) {
            arr[start] = arr[left];
            arr[left] = pivot;
            p = left;
        } else {
            arr[start] = arr[left - 1];
            arr[left - 1] = pivot;
            p = left - 1;
        }
        int qk = p - start + 1;//主元是第几个
        if (k == qk) return arr[p];
        else if ((k > qk) && (p + 1 <= end)) return SelectK(arr, p + 1, end, k - qk);//注意参数变化
        else if ((k > qk) && (p + 1 > end)) return arr[end];
        else if ((k < qk) && (p - 1 >= start)) return SelectK(arr, start, p - 1, k);
        else return arr[start];
    }

    public static int SelectK2(int[] arr, int start, int end, int k) {
        if (k > arr.length) return -1;
        if (start == end) return arr[end];
        int pivot = arr[start];
        int left = start + 1;
        int right = end;
        while (left < right) {
            //右指针左移动，找第一个比pivot小的值
            while ((left < right) && (arr[right] > pivot)) {
                right--;
            }
            //左指针右移动，找第一个比pivot大的值
            while ((left < right) && (arr[left] <= pivot)) {
                left++;
            }
            if ((arr[right] != arr[left]) && (left < right)) {
                int temp = arr[right];
                arr[right] = arr[left];
                arr[left] = temp;
            }
        }
        int p;//哨兵下标
        if (arr[left] <= pivot) {
            arr[start] = arr[left];
            arr[left] = pivot;
            p = left;
        } else {
            arr[start] = arr[left - 1];
            arr[left - 1] = pivot;
            p = left - 1;
        }
        int qk = p+1;//目标下标
        if (k == qk) return arr[p];  //如果k等于目标下标+1
        else if (k > qk) return SelectK2(arr, p + 1, end, k);//注意参数变化
        else return SelectK2(arr, start, p - 1, k);
    }
}
