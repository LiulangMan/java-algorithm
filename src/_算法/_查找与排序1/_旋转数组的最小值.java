package _算法._查找与排序1;


/***
 *
 * 二分法，最小值总在无序的一边
 *
 *终点：两个值，最小值在右侧
 *
 * ***/
public class _旋转数组的最小值 {
    public static void main(String[] args) {
        int[] arr = {3, 4, 5, 1, 2};
        int mid = arr.length / 2;
        int start = 0;
        int end = arr.length - 1;
        if (arr[start] == arr[end]) {//如果遇到这种情况，得放弃while体内的算法
            int p = arr[0];
            for (int i = 1; i < arr.length; i++) {
                if (arr[i] < p) p = arr[i];
            }
            System.out.println(p);
            return;
        }
        while (true) {
            if (arr[mid] > arr[start]) {//左侧有序
                start = mid;
                mid = end - (end - mid) / 2;

            } else {//右侧有序
                end = mid;
                mid = start + (mid - start) / 2;
            }
            if (end - start == 1) {//最后只剩两个元素
                System.out.println(arr[end]);
                return;
            }

        }

    }
}
