package _算法._查找与排序2;


import static _算法._查找与排序2._第k小的数.SelectK;

/***
 *
 * 寻找最小ID：整齐的数组里，找最小间断点
 * 方法：
 * 1：Array.Sort(arr) + 顺序扫描
 * 2：辅助空间+顺扫
 * 3：快排变体：寻找中间值，如果下标+1等于元素值，则左侧紧密，递归下去~~~
 *
 * ***/
public class _寻找最小ID {
    public static void main(String[] args) {
        int[] arr = {3, 4, 2, 6, 7, 5};
        int ans3 = find3(arr, 0, arr.length - 1);
        System.out.println(ans3);
    }

    private static int find3(int[] arr, int start, int end) {
        //方法3
        if (start > end) return start + 1;
        int mid = (start + end) >> 1;//取中
        int valueofmid = SelectK(arr, start, end, mid + 1);
        if (valueofmid == mid + 1) {//左侧紧密
            return find3(arr, mid + 1, end);
        } else {
            return find3(arr, start, mid - 1);
        }

    }
}
