package _算法._查找与排序2;

/**
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组,求出这个数组中的逆序对的总数P。
 * 并将P对1000000007取模的结果输出。 即输出P%1000000007
 *
 * */
public class _归并排序_找逆序对 {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        int[] arr = {1,5,3,2,1,5,6};
        System.out.println(Solution.class.newInstance().InversePairs(arr));
    }

}

 class Solution {
    public int InversePairs(int[] array) {
        return f(array, 0, array.length - 1);
    }

    private int f(int[] array, int lo, int hi) {
        if (lo == hi) return 0;

        int mid = (lo + hi) >> 1;

        int cnt1 = f(array, lo, mid);
        int cnt2 = f(array, mid + 1, hi);

        //然后 归并 排序 且统计
        //每个排序块 对另一块 没有影响
        int p = mid + 1;
        int cnt3 = 0;
        int[] help = new int[hi - lo + 1];
        int index = 0;
        int p1 = lo;
        int p2 = mid + 1;
        while (p1 <= mid || p2 <= hi) {
            if (p1 <= mid && p2 <= hi) {
                int min = Math.min(array[p1], array[p2]);

                if (min == array[p2] && min != array[p1] ) {
                    cnt3 += mid - p1 + 1;
                    cnt3 %= 1000000007;
                    p2++;
                } else {
                    p1++;
                }

                help[index++] = min;
            } else if (p1 <= mid) {
                //
                //cnt3 += mid - p1 + 1;
                help[index++] = array[p1++];
            } else {
                help[index++] = array[p2++];
            }
        }

        //归并原数组
        index = 0;
        for (int i = lo; i <= hi;i++ ) {
            array[i] = help[index++];
        }

        return (cnt1 + cnt2 + cnt3) % 1000000007;
    }
}