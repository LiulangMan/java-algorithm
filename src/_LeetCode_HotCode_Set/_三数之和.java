package _LeetCode_HotCode_Set;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***
 *
 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。

 注意：答案中不可以包含重复的三元组。
 *
 * ***/

public class _三数之和 {
    public static void main(String[] args) {
        System.out.println(threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
    }

    private static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        if (nums.length <3)return list;
        Arrays.sort(nums);//nlogn
        for (int i = 0; i < nums.length; i++) {
            if (nums[i]>0)break;
            if (i-1>=0 && nums[i]==nums[i-1])continue;//去重
            int target = -nums[i];//化简为两数之和

            int left = i+1;
            int right = nums.length-1;

            while (left <right){
                if (nums[left] + nums[right] == target){
                    list.add(new ArrayList<>(Arrays.asList(nums[i],nums[left],nums[right])));
                    left++; right--; // 首先无论如何先要进行加减操作
                    while (left < right && nums[left] == nums[left - 1]) left++;//同理，去重
                    while (left < right && nums[right] == nums[right + 1]) right--;
                }else if (nums[left]+nums[right]>target){
                    right--;
                }else {
                    left++;
                }
            }
        }
        return list;
    }


}
