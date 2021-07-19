package _算法._查找与排序1;
/***
 * 条件，已排序好的算法，O（t） = log n
 * 算法：三指针，mod、left、right
 * if value>arr[mod]新一轮：right = right,left = mod,mod = mod+(right-mod+1)/2
 * else 新一轮:right = mod,left = left,mod = mod - (mod-left+1)/2
 * 终止 right = left
 *
 * ***/
public class _二分查找的递归解法 {
    public static void main(String[] args) {
        int[] arr = {1, 5, 7, 9, 12, 15, 17};
        int ans = find(arr, 19, arr.length / 2, 0, arr.length - 1);
        System.out.println(ans);
    }

    private static int find(int[] arr, int value, int half, int left, int right) {
        //System.out.println(arr[half]);
        if (right == left) return -1;//未查到

        if (arr[half] == value) return half;//返回结果

        if (arr[half] < value) return find(arr, value, half + (right - half + 1) / 2, half, right);
        else return find(arr, value, half - (half - left + 1) / 2, left, half);  //+1是为了避免约掉
    }
}
