package _算法._递归_DFS_回溯_剪枝;

import java.util.HashSet;
import java.util.Set;

/***
 * 给定集合，返回所有非空子集
 *
 * 即是 选入和不选入的问题
 *
 * ***/
public class _返回所有子集 {
    public static void main(String[] args) {
        char[] arr = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        Set<Set<Character>> ans = findway(arr, arr.length - 1);

        System.out.println(ans.size());
        for (Set<Character> set1: ans) {
            for (Character chr:set1) {
                System.out.print(chr);
            }
            System.out.println();
        }
    }

    private static Set<Set<Character>> findway(char[] arr, int cur) {
        Set<Set<Character>> newSet = new HashSet<>();

        if (cur == 0){
            Set<Character> firstSet = new HashSet<>();
            Set<Character> firstSet_k = new HashSet<>();
            firstSet.add(arr[0]);
            newSet.add(firstSet);
            newSet.add(firstSet_k);
            return newSet;
        }

        Set<Set<Character>> oldSet = findway(arr, cur - 1);

        for (Set<Character> eOfold : oldSet) {
            newSet.add(eOfold);//不加

            Set<Character> clone = (Set<Character>)((HashSet) eOfold).clone();//java里的拷贝
            clone.add(arr[cur]);//加入
            newSet.add(clone);
        }
        return newSet;
    }
}
