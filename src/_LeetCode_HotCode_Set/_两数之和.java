package _LeetCode_HotCode_Set;

import java.util.*;

/***
 *
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * ***/
class Beg implements Comparable<Beg> {
    int index;
    int value;

    public Beg(int index, int value) {
        this.index = index;
        this.value = value;
    }


    @Override
    public int compareTo(Beg o) {
        return value - o.value;
    }
}

public class _两数之和 {
    public static void main(String[] args) {
        twoSum(new int[]{3, 2, 4}, 6);
        TreeMap<Integer,Integer> map = new TreeMap<>();
    }


    private static int[] twoSum(int[] nums, int target) {
        Beg[] begs = new Beg[nums.length];
        for (int i = 0; i < nums.length; i++) {
            begs[i] = new Beg(i, nums[i]);
        }
        Arrays.sort(begs);

        int lo = 0;
        int hi = begs.length - 1;
        while (lo < hi) {
            if (begs[lo].value + begs[hi].value == target) {
                return new int[]{Math.min(begs[lo].index, begs[hi].index), Math.max(begs[lo].index, begs[hi].index)};
            }
            if (begs[lo].value + begs[hi].value < target) lo++;
            if (begs[lo].value + begs[hi].value > target) hi--;
        }
        return new int[]{};
    }
}
