package _LeetCode_HotCode_Set;

import org.omg.PortableInterceptor.INACTIVE;


import java.util.*;


/***
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的数字可以无限制重复被选取。
 *
 * 说明：
 *
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。 
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * 典型回溯算法题
 * ***/
public class _组合数 {
    public static void main(String[] args) {
        System.out.println(combinationSum(new int[]{2,3,7},7));
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(candidates);//保证顺序，便于剪枝

        List<Integer> path = new ArrayList<>();
        f(target,candidates,path,list,0);

        return list;
    }

    private static void f(int current, int[] candidates, List<Integer> path, List<List<Integer>> list,int num) {
        if (current == 0){
            List<Integer> ans = new ArrayList<>(path) ;
            list.add(ans);
            return;
        }

        for (int i = num;i<candidates.length && current-candidates[i]>=0;i++) {
            path.add(candidates[i]);
            f(current-candidates[i], candidates, path, list,i);
            path.remove(path.size() - 1);//回溯
        }
    }
}
