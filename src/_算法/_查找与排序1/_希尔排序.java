package _算法._查找与排序1;


/***
 *
 * 希尔排序（缩小增量排序），是插入排序的一种变体，非稳定插入排序 O(t) = log n ~~~~ n^2
 * 增量：用于分组，组内插入排序，然后缩小增量（缩小一半）
 *
 *
 * ***/
public class _希尔排序 {
    public static void main(String[] args) {
        int[] arr = {5,1,2,5,7,7,3,2,1,2,4};
        shellSort(arr);
        for (int m: arr) {
            System.out.print(m+" ");
        }
    }

    public static void shellSort(int[] arr) {
        for (int interval = arr.length / 2; interval > 0; interval = interval / 2) {
            //增量为interval的插入排序
            for (int i = interval; i < arr.length; i++) {
                int target = arr[i];
                int j = i - interval;
                while (j > -1 && target < arr[j]) {
                    arr[j + interval] = arr[j];
                    j -= interval;
                }
                arr[j + interval] = target;
            }
        }
    }
}
