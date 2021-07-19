package _算法._查找与排序2;

import org.junit.Test;

/***
 * 分治法：快排
 *
 * nlogn
 *
 * 工业优化：哨兵取三点中值法，规模较小取插入排序
 * ***/
public class _快速排序 {
    public static void main(String[] args) {
        int[] arr = {1, 4, 6, 2, 3, 5, 7, 5, 3, 6, 7, 8, 9, 5};
        qSort(arr, 0, arr.length - 1);
        for (int m : arr) {
            System.out.print(m + " ");
        }
    }

    @Test
    public void test1(){
        int[] arr = {5,2,1,2,5,6,7,8,8,3,5,7,};
        qSort(arr,0,arr.length-1);
        for (int a:arr) {
            System.out.print(a);
        }
    }
    private static void qSort(int[] arr, int start, int end) {
        //双指针扫描
        int pivot = arr[start];  //哨兵
        int i = start;
        int j = end;
        while (i < j) {

            while ((arr[j] > pivot) && (i < j)) {  //找出第一个小的值
                j--;
            }
            while ((arr[i] < pivot) && (i < j)) {  //找出第一个大的值
                i++;
            }
            if ((arr[i] == arr[j]) && (i < j)) i++;  //相等，i++，没必要交换
            else {
                //交换
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        if (i - 1 > start) qSort(arr, start, i - 1);
        if (j + 1 < end) qSort(arr, j + 1, end);
    }


    private static void qSort2(int[] arr, int start, int end){
        //单扫
        if (start < end){
        int q  = partion(arr,start,end);
        qSort2(arr,start,q-1);
        qSort2(arr,q+1,end);
        }
    }

    private static int partion(int[] arr, int start, int end) {
        int pivot = arr[start];
        int sp = start+1;//扫描指针
        int bigger = end;//尾指针
        while (sp<=bigger){
            if (arr[sp]>pivot){
                int temp = arr[sp];
                arr[sp] = arr[bigger];
                arr[bigger] = temp;
                bigger--;
            }
            else sp++;
        }
        int temp = arr[start];
        arr[start] = arr[bigger];
        arr[bigger] = temp;
        return bigger;
    }
}
