package _算法._查找与排序2;


/***
 * 二叉树的结构
 * 步骤：1，乱序数组的堆化
 *      2，选出堆顶，数组减1
 *      3，调整堆
 *      反复
 * ***/
public class _堆排序 {
    public static void main(String[] args) {
        int[] arr = {2, 3, 1, 6, 3, 4, 9, 6, 4, 6, 1};
        heapSort(arr);//升序排序
        for (int m : arr) {
            System.out.print(m + " ");
        }
    }

    private static void heapSort(int[] arr) {
        buildMinHeap(arr);//建最小堆
        selectTopToEnd(arr, arr.length - 1);//选取堆顶
        reserveArr(arr);
    }

    private static void reserveArr(int[] arr) {
        //顺序反转
        for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    private static void selectTopToEnd(int[] arr, int end) {
        if (end == 0) return;
        int temp = arr[0];//交换最后与堆顶元素
        arr[0] = arr[end];
        arr[end] = temp;
        ReBuildMinHeap(arr, 0, end - 1, false);//重新调整堆
        selectTopToEnd(arr, end - 1);//递归实现
    }

    private static void buildMinHeap(int[] arr) {
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            ReBuildMinHeap(arr, i, arr.length - 1, true);//全局调整堆
        }
    }

    /***
     * @param arr:待排序数组
     * @param i：排序根节点起点
     * @param end：数组长度
     * @param first: 是否第一次建堆
     * ***/
    private static void ReBuildMinHeap(int[] arr, int i, int end, boolean first) {
        int left = 2 * i + 1;//左节点
        int right = 2 * i + 2;//右节点

        if (left > end) return;//出口
        if (right <= end) {//有两子节点时
            //选最小元素的下标
            int min = arr[i] > arr[left] ? left : i;
            min = arr[min] > arr[right] ? right : min;
            if (min == i) {
                if (!first) return;//不用调整了
                ReBuildMinHeap(arr, left, end, true);
                ReBuildMinHeap(arr, right, end, true);
            }

            //交换
            int temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
            if (min == left) ReBuildMinHeap(arr, left, end, first);
            else ReBuildMinHeap(arr, right, end, first);

        } else {//只有一个节点
            int min = arr[i] > arr[left] ? left : i;
            if (min == i) return;

            //交换
            int temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;

        }
    }

}
