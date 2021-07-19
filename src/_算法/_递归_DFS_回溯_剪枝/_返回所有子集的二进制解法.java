package _算法._递归_DFS_回溯_剪枝;

import java.util.ArrayList;

public class _返回所有子集的二进制解法 {
    public static void main(String[] args) {
        char[] arr = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        ArrayList<ArrayList<Character>> ans = findway(arr);

        System.out.println(ans.size());
        for (ArrayList<Character> list1 : ans) {
            System.out.println(list1);
        }
    }

    private static ArrayList<ArrayList<Character>> findway(char[] arr) {
        //Arrays.sort(arr);//是否字典序排序

        int n = arr.length;
        int ForLen = pow(2, n) - 1;  //写一个pow函数
        ArrayList<ArrayList<Character>> ans = new ArrayList<>();
        for (int i = 1; i <= ForLen; i++) {

            ArrayList<Character> list = new ArrayList<>();

            for (int j = n - 1; j >= 0; j--) {
                if (((i >> j) & 1) == 1) {
                    //check某位上的是否为1
                    list.add(arr[j]);
                }
            }

            ans.add(list);
        }
        return ans;
    }


    public static int pow(int num, int n) {
        if (num == 0) return 0;
        if (n == 0) return 1;
        int ans = 1;
        int k = num;
        while (n != 0) {
            if ((n & 1) == 1) {
                ans *= k;
            }
            k *= k;
            n >>>= 1;
        }
        return ans;
    }
}
